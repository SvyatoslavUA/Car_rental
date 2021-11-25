package lpnu.service;

import lpnu.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO saveUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUserById(long id);
}
