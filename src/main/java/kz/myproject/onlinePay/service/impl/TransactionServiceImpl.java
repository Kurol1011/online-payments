package kz.myproject.onlinePay.service.impl;


import jakarta.transaction.Transactional;
import kz.myproject.onlinePay.entity.BankAccount;
import kz.myproject.onlinePay.repo.BankAccountRepository;
import kz.myproject.onlinePay.repo.TransactionRepository;
import kz.myproject.onlinePay.service.intf.TransactionService;
import kz.myproject.onlinePay.util.exception.BankAccountTransactionException;
import org.springframework.transaction.annotation.Isolation;

import java.math.BigDecimal;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    //todo MAKE TRANSACTIONAL
    @Override
    @Transactional
    public void makeTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal transferAmount) {
        if(fromBankAccount.getBalance().compareTo(transferAmount) == -1){
            throw new BankAccountTransactionException("Недостаточно средств на счете!");
        }
        fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(transferAmount));
        toBankAccount.setBalance(toBankAccount.getBalance().add(transferAmount));
        bankAccountRepository.save(fromBankAccount);
        bankAccountRepository.save(toBankAccount);
    }
}
