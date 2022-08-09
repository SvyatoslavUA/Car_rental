package lpnu.mapper;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserMapperDTO {
    public User toEntity(final UserDTO userDTO){
        final User user = new User();

        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());


        return user;
    }

    public UserDTO toDTO(final User user){
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setOrderIds(user.getOrders().stream().map(e -> e.getId()).collect(Collectors.toList()));
        return userDTO;
    }
}
