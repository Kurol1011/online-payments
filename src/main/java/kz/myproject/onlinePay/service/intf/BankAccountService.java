package kz.myproject.onlinePay.service.intf;

import kz.myproject.onlinePay.entity.BankAccount;
import kz.myproject.onlinePay.entity.User;


public interface BankAccountService {
    void createNewAccount(User user,long balance);

    void addBalance(long balance, long bankAccountId);
    double getBalance(User user);

    BankAccount getById(long id);
}
