package kz.myproject.onlinePay.service.impl;

import kz.myproject.onlinePay.dto.UserDTO;
import kz.myproject.onlinePay.entity.User;
import kz.myproject.onlinePay.repo.UserRepository;
import kz.myproject.onlinePay.security.AuthUserDetails;
import kz.myproject.onlinePay.service.intf.UserService;
import kz.myproject.onlinePay.util.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBankAccounts(user.getBankAccounts().stream().map(ac -> ac.getId()).collect(Collectors.toList()));
        return userDTO;
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        return userRepository.findByEmail(user.getUsername()).orElseThrow(()->new UserNotFoundException("user not found by email!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
