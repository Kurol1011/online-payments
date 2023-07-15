package kz.myproject.onlinePay.controller;

import jakarta.validation.Valid;
import kz.myproject.onlinePay.security.AuthenticationRequest;
import kz.myproject.onlinePay.security.AuthenticationResponse;
import kz.myproject.onlinePay.security.AuthenticationService;
import kz.myproject.onlinePay.security.RegisterRequest;
import kz.myproject.onlinePay.util.exception.RegisterRequestDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult){
        //logger.info("start register service");
        if (bindingResult.hasErrors()) {
            throw new RegisterRequestDataException(bindingResult);
        }
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        //logger.info("start login service");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    private String getValidationErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        }
        return errorMessage.toString();
    }
}

