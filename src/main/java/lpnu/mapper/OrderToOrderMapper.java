package lpnu.mapper;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderMapper {
    @Autowired
    private CarToCarMapperDTO carToCarDTOMapper;
    @Autowired
    private UserToUserMapperDTO userToUserMapperDTO;
    public Order toEntity(final OrderDTO orderDTO){
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCar(carToCarDTOMapper.toEntity(orderDTO.getCar()));
        order.setUser(userToUserMapperDTO.toEntity(orderDTO.getUser()));
        order.setNightBonus(orderDTO.getNightBonus());
        order.setActive(orderDTO.isActive());

        return order;
    }

    public OrderDTO toDTO(final Order order){
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCar(carToCarDTOMapper.toDTO(order.getCar()));
        orderDTO.setUser(userToUserMapperDTO.toDTO(order.getUser()));
        orderDTO.setNightBonus(order.getNightBonus());
        orderDTO.setActive(order.isActive());

        return orderDTO;
    }
}
