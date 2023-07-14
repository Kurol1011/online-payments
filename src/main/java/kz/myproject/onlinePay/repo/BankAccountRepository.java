package kz.myproject.onlinePay.repo;

import kz.myproject.onlinePay.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

}
