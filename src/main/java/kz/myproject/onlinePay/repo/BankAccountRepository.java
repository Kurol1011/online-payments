package kz.myproject.onlinePay.repo;

import kz.myproject.onlinePay.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    Optional<BankAccount> findByOwner_id(long id);
}
