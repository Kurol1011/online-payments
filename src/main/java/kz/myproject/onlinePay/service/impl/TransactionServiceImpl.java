package kz.myproject.onlinePay.service.impl;


import jakarta.transaction.Transactional;
import kz.myproject.onlinePay.entity.BankAccount;
import kz.myproject.onlinePay.repo.BankAccountRepository;
import kz.myproject.onlinePay.repo.TransactionRepository;
import kz.myproject.onlinePay.service.intf.TransactionService;
import kz.myproject.onlinePay.service.intf.UserService;
import kz.myproject.onlinePay.util.exception.BankAccountTransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.math.BigDecimal;

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
    @Transactional
    public void makeTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal transferAmount) {
        if(fromBankAccount.getBalance().compareTo(transferAmount) == -1){
            throw new BankAccountTransactionException("Недостаточно средств на счете!");
        }
        else if(transferAmount.compareTo(BigDecimal.valueOf(1)) == -1){
            throw new BankAccountTransactionException("Сумма перевода должна быть ровна 1$ либо больше!");
        }
        else if( !userService.getCurrentUser().getBankAccounts().stream().anyMatch(ac -> ac.getId() == fromBankAccount.getId())){
            throw new BankAccountTransactionException("Введенный счет в поле fromBankAccount не является вашим!");
        }
        else if(bankAccountRepository.findById(toBankAccount.getId()).isEmpty()){
            throw new BankAccountTransactionException("Ввведенный счет в поле toBankAccount не существует!");
        }
        fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(transferAmount));
        toBankAccount.setBalance(toBankAccount.getBalance().add(transferAmount));
        bankAccountRepository.save(fromBankAccount);
        bankAccountRepository.save(toBankAccount);
    }
}
