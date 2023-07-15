package kz.myproject.onlinePay.controller;

import kz.myproject.onlinePay.dto.TransactionData;
import kz.myproject.onlinePay.entity.Transaction;
import kz.myproject.onlinePay.service.intf.BankAccountService;
import kz.myproject.onlinePay.service.intf.TransactionService;
import kz.myproject.onlinePay.service.intf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    private final UserService userService;

    private final BankAccountService bankAccountService;

    public TransactionController(TransactionService transactionService, UserService userService, BankAccountService bankAccountService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/make-transfer")
    public ResponseEntity<?> makeTransfer(@RequestBody TransactionData transactionData){
        transactionService.makeTransfer( transactionData.getFromBankAccount(),transactionData.getToBankAccount(), BigDecimal.valueOf(transactionData.getAmount()));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
