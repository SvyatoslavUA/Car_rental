package lpnu.service;

import lpnu.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO saveOrder(OrderDTO user);
    OrderDTO updateOrder(OrderDTO user);
    void deleteOrderById(Long id);
}
