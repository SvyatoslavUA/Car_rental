package lpnu.service.impl;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import lpnu.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private final OrderToOrderMapper orderMapper;

    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final OrderToOrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.getAllOrders().stream()
                .map(e -> orderMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(final Long id) {
        return orderMapper.toDTO(orderRepository.getOrderById(id));
    }

    @Override
    public OrderDTO saveOrder(final OrderDTO orderDTO) {

        final Order order = orderMapper.toEntity(orderDTO);

        orderRepository.saveOrder(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO updateOrder(final OrderDTO orderDTO) {

        final Order order = orderMapper.toEntity(orderDTO);

        return orderMapper.toDTO(orderRepository.updateOrder(order));
    }

    @Override
    public void deleteOrderById(final Long id) {
        orderRepository.deleteOrderById(id);
    }
}
