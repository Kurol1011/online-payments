package kz.myproject.onlinePay.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import kz.myproject.onlinePay.entity.BankAccount;

import java.util.List;

public class UserDTO {

    private long id;


    private String firstName;


    private String lastName;


    private String email;


    private List<Long> bankAccounts;

    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, String email, List<Long> bankAccounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bankAccounts = bankAccounts;
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

    public List<Long> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<Long> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
