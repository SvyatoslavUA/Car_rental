package lpnu.service.impl;

import lpnu.dto.OrderDTO;
import lpnu.entity.Car;
import lpnu.entity.Order;
import lpnu.exception.ServiceException;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.repository.OrderRepository;

import org.aspectj.weaver.ast.Or;
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
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(final Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new ServiceException(400, "User with id not found: " + id, "")));
    }

    @Override
    public OrderDTO saveOrder(final OrderDTO orderDTO) {

        final Order order = orderMapper.toEntity(orderDTO);

        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO updateOrder(final OrderDTO orderDTO) {
        if(orderDTO.getId() == null){
            //   throw new

        }
        final Order savedOrder = orderRepository.findById(orderDTO.getId()).orElseThrow(() -> new ServiceException(400, "Car with id not found: " + orderDTO.getId(), ""));

        final Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public void deleteOrderById(final Long id) {
        orderRepository.deleteById(id);
    }
}
