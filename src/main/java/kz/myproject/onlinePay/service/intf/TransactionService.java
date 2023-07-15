package kz.myproject.onlinePay.service.intf;

import kz.myproject.onlinePay.entity.BankAccount;

import java.math.BigDecimal;

public interface TransactionService {
    void makeTransfer(long fromBankAccount, long toBankAccount, BigDecimal transferAmount);
}
