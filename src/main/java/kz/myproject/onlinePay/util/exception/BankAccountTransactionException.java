package kz.myproject.onlinePay.util.exception;

public class BankAccountTransactionException extends RuntimeException{
    public BankAccountTransactionException(String message) {
        super(message);
    }
}
