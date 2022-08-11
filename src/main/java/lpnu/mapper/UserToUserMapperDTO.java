package lpnu.mapper;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserMapperDTO {
    @Autowired
    private OrderRepository orderRepository;

    public void order(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public User toEntity(final UserDTO userDTO){
        final User user = new User();


        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUserRole(userDTO.getUserRole());
        user.setOrders(orderRepository.findAllByIdIn(userDTO.getOrderIds()));
        return user;
    }

    public UserDTO toDTO(final User user){
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setUserRole(user.getUserRole());
        userDTO.setOrderIds(user.getOrders().stream().map(e -> e.getId()).collect(Collectors.toList()));
        return userDTO;
    }
}
