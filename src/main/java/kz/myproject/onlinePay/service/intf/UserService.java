package kz.myproject.onlinePay.service.intf;

import kz.myproject.onlinePay.dto.UserDTO;
import kz.myproject.onlinePay.entity.User;

public interface UserService {
    //User convertToUser(UserDTO userDTO);
    UserDTO convertToUserDTO(User user);
    User getById(long id);
    User getByEmail(String email);
}
