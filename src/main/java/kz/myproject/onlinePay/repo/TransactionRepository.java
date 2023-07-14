package kz.myproject.onlinePay.repo;

import kz.myproject.onlinePay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
