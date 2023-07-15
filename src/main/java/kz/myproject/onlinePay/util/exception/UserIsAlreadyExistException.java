package kz.myproject.onlinePay.util.exception;

public class UserIsAlreadyExistException extends RuntimeException{
    public UserIsAlreadyExistException(String message) {
        super(message);
    }
}
