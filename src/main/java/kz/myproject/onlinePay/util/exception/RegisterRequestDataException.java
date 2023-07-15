package kz.myproject.onlinePay.util.exception;

import org.springframework.validation.BindingResult;

public class RegisterRequestDataException extends RuntimeException{
    private BindingResult bindingResult;

    public RegisterRequestDataException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
