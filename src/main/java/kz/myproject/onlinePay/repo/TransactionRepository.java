package kz.myproject.onlinePay.repo;

import kz.myproject.onlinePay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
