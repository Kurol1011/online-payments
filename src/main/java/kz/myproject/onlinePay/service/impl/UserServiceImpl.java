package kz.myproject.onlinePay.service.impl;

import kz.myproject.onlinePay.dto.UserDTO;
import kz.myproject.onlinePay.entity.User;
import kz.myproject.onlinePay.repo.UserRepository;
import kz.myproject.onlinePay.service.intf.UserService;

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
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setBankAccounts(user.getBankAccounts());
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
}
