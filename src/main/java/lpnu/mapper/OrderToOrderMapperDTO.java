package lpnu.mapper;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderMapperDTO {
    public Order toEntity(final OrderDTO orderDTO){
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCar(orderDTO.getCar());
        order.setUser(orderDTO.getUser());
        order.setNightBonus(orderDTO.getNightBonus());
        order.setActive(orderDTO.isActive());

        return order;
    }

    public OrderDTO toDTO(final Order order){
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCar(order.getCar());
        orderDTO.setUser(order.getUser());
        orderDTO.setNightBonus(order.getNightBonus());
        orderDTO.setActive(order.isActive());

        return orderDTO;
    }
}
