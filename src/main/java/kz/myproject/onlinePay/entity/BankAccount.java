package kz.myproject.onlinePay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;


@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long id;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName = "user_id")
    private User owner;


    @Column(name="created_date")
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) todo test this date format
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    public BankAccount() {
    }

    public BankAccount(long id, BigDecimal balance, Currency currency, User owner, Date createdDate) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.owner = owner;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
