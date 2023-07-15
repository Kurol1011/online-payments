package kz.myproject.onlinePay.controller;

import kz.myproject.onlinePay.dto.UserDTO;
import kz.myproject.onlinePay.service.intf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(){
        return ResponseEntity.ok(userService.convertToUserDTO(userService.getCurrentUser()));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> list = userService.getAllUsers().stream().map(u -> userService.convertToUserDTO(u)).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
