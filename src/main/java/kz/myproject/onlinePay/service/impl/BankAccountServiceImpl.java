package kz.myproject.onlinePay.service.impl;

import kz.myproject.onlinePay.entity.BankAccount;
import kz.myproject.onlinePay.entity.User;
import kz.myproject.onlinePay.repo.BankAccountRepository;
import kz.myproject.onlinePay.service.intf.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void createNewAccount(User user,long balance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(user);
        bankAccount.setCurrency(Currency.getInstance("USD"));
        bankAccount.setBalance(BigDecimal.valueOf(balance));
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void setBalance(long newBalance,long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).get();
        bankAccount.setBalance(bankAccount.getBalance().add(BigDecimal.valueOf(newBalance)));
        bankAccountRepository.save(bankAccount);
    }


    @Override
    public double getBalance(User user) {
        return bankAccountRepository.findByOwner_id(user.getId()).get().getBalance().doubleValue();
    }

    @Override
    public BankAccount getById(long id) {
        return bankAccountRepository.findById(id).get();
    }
}
