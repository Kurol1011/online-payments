package kz.myproject.onlinePay.controller;

import kz.myproject.onlinePay.dto.BalanceRequest;
import kz.myproject.onlinePay.service.intf.BankAccountService;
import kz.myproject.onlinePay.service.intf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankAccountController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @PostMapping("/create-new-account")
    public ResponseEntity<?> createNewAccount(){
        bankAccountService.createNewAccount(userService.getCurrentUser(),245);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/my-balance")
    public ResponseEntity<?> showBalance(){
        return ResponseEntity.ok(bankAccountService.getBalance(userService.getCurrentUser()));
    }

    @GetMapping("/other-balance")
    public ResponseEntity<?> showBalanceOtherUser(@RequestParam String email){
        return ResponseEntity.ok(bankAccountService.getBalance(userService.getByEmail(email)));

    }
}
