package kz.myproject.onlinePay.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "from_bank_account")
    private long fromBankAccount;

    @Column(name = "to_bank_account")
    private long toBankAccount;

    @Column(name = "transfer_amount")
    private BigDecimal transferAmount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(long id, long fromBankAccount, long toBankAccount, BigDecimal transferAmount, LocalDateTime transactionDate) {
        this.id = id;
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.transferAmount = transferAmount;
        this.transactionDate = transactionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromBankAccount() {
        return fromBankAccount;
    }

    public void setFromBankAccount(long fromBankAccount) {
        this.fromBankAccount = fromBankAccount;
    }

    public long getToBankAccount() {
        return toBankAccount;
    }

    public void setToBankAccount(long toBankAccount) {
        this.toBankAccount = toBankAccount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
