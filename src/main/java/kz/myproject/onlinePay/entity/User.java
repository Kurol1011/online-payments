package kz.myproject.onlinePay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kz.myproject.onlinePay.entity.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name="first_name")
    @NotEmpty(message = "firstName should not be empty")
    @Pattern(regexp = "\\b[A-Z][a-z]*\\b",message = "first Name is not valid")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message = "lastName should not be empty")
    @Pattern(regexp = "\\b[A-Z][a-z]*\\b",message = "last Name is not valid")
    private String lastName;

    @Column(name="email")
    @Email(message = "enter the valid email!")
    @NotEmpty(message = "email should not be empty")
    private String email;

    @Column(name="password")
    @NotEmpty(message = "password should not be empty")
    //@Size(min = 2,max = 45, message = "Password must be longer 2 characters and less than 45")
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<BankAccount> bankAccounts;

    @Column(name="created_date")
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) todo test this date format
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(long id, String firstName, String lastName, String email, String password, List<BankAccount> bankAccounts, Date createdDate, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bankAccounts = bankAccounts;
        this.createdDate = createdDate;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
