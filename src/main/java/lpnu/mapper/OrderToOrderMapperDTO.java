package lpnu.mapper;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderMapperDTO {
    public Order toEntity(final OrderDTO orderDTO){
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setPriceOfOrder(orderDTO.getPriceOfOrder());
        order.setUser(orderDTO.getUser());
        order.setNightBonus(orderDTO.getNightBonus());

        return order;
    }

    public OrderDTO toDTO(final Order order){
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setPriceOfOrder(orderDTO.getPriceOfOrder());
        orderDTO.setUser(orderDTO.getUser());
        orderDTO.setNightBonus(orderDTO.getNightBonus());

        return orderDTO;
    }
}
