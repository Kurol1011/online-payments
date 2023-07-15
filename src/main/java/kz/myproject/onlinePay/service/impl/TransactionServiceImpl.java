package kz.myproject.onlinePay.service.impl;


import com.zaxxer.hikari.util.IsolationLevel;
import jakarta.transaction.Transactional;
import kz.myproject.onlinePay.entity.BankAccount;
import kz.myproject.onlinePay.entity.Transaction;
import kz.myproject.onlinePay.repo.BankAccountRepository;
import kz.myproject.onlinePay.repo.TransactionRepository;
import kz.myproject.onlinePay.service.intf.TransactionService;
import kz.myproject.onlinePay.service.intf.UserService;
import kz.myproject.onlinePay.util.exception.BankAccountTransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    private final UserService userService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.userService = userService;
    }

    //todo MAKE TRANSACTIONAL
    @Override
    @Transactional()
    public void makeTransfer(long fromBankAccountId, long toBankAccountId, BigDecimal transferAmount) {

        Optional<BankAccount> fromBankAccount = bankAccountRepository.findById(fromBankAccountId);
        Optional<BankAccount> toBankAccount = bankAccountRepository.findById(toBankAccountId);

        if(fromBankAccount.isEmpty()
        || !userService.getCurrentUser().getBankAccounts().stream().anyMatch(ac -> ac.getId() == fromBankAccount.get().getId())
        ){
            throw new BankAccountTransactionException("Введенный счет в поле fromBankAccount не является вашим либо он не существует!");
        }
        else if(toBankAccount.isEmpty()){
            throw new BankAccountTransactionException("Ввведенный счет в поле toBankAccount не существует!");
        }
        else if(fromBankAccount.get().getBalance().compareTo(transferAmount) == -1){
            throw new BankAccountTransactionException("Недостаточно средств на счете!");
        }
        else if(transferAmount.compareTo(BigDecimal.valueOf(1)) == -1){
            throw new BankAccountTransactionException("Сумма перевода должна быть ровна 1$ либо больше!");
        }
        fromBankAccount.get().setBalance(fromBankAccount.get().getBalance().subtract(transferAmount));
        toBankAccount.get().setBalance(toBankAccount.get().getBalance().add(transferAmount));
        bankAccountRepository.save(fromBankAccount.get()); //надо бы сюда каскадирование прикрутить
        bankAccountRepository.save(toBankAccount.get());
        Transaction transaction = new Transaction();
        transaction.setFromBankAccount(fromBankAccountId);
        transaction.setToBankAccount(toBankAccountId);
        transaction.setTransferAmount(transferAmount);
        transactionRepository.save(transaction);

    }
}
