package kz.myproject.onlinePay.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kz.myproject.onlinePay.entity.enums.Role;


public class RegisterRequest {
    @NotEmpty(message = "firstName should not be empty")
    @Pattern(regexp = "\\b[A-Z][a-z]*\\b",message = "first Name is not valid")
    private String firstName;
    @NotEmpty(message = "lastName should not be empty")
    @Pattern(regexp = "\\b[A-Z][a-z]*\\b",message = "last Name is not valid")
    private String lastName;

    @Email(message = "enter the valid email!")
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2,max = 45, message = "Password must be longer 2 characters and less than 45")
    private String password;

    private Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
