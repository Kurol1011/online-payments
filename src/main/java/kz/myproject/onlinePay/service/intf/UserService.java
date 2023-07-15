package kz.myproject.onlinePay.service.intf;

import kz.myproject.onlinePay.dto.UserDTO;
import kz.myproject.onlinePay.entity.User;

import java.util.List;

public interface UserService {
    //User convertToUser(UserDTO userDTO);
    UserDTO convertToUserDTO(User user);
    User getById(long id);
    User getByEmail(String email);

    User getCurrentUser();

    List<User> getAllUsers();
}
