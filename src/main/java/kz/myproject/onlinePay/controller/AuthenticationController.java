package kz.myproject.onlinePay.controller;


import kz.myproject.onlinePay.security.AuthenticationRequest;
import kz.myproject.onlinePay.security.AuthenticationResponse;
import kz.myproject.onlinePay.security.AuthenticationService;
import kz.myproject.onlinePay.security.RegisterRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        //logger.info("start register service");
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        //logger.info("start login service");
            return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

