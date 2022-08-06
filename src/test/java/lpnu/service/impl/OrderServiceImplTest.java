package lpnu.service.impl;

import lpnu.dto.OrderDTO;
import lpnu.entity.Car;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;
import lpnu.entity.enumeration.UserRole;
import lpnu.exception.ServiceException;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.repository.OrderRepository;
import lpnu.service.OrderService;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    @Test
    public void test_getOrderById_orderExist() throws Exception{
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final User user = new User(1L, "name", "", "","", UserRole.MANAGER);
        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Order order = new Order(1L, car,150, 1.30, 2,user, true);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderMapper.toDTO(any())).thenCallRealMethod();


        final OrderDTO orderDTO = orderService.getOrderById(1L);

        assertNotNull(orderDTO);
        assertEquals(1L, (long)orderDTO.getId());
        assertEquals(order.getCar(), orderDTO.getCar());
    }

    @Test
    public void test_getOrderById_orderNotExist() throws Exception{
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final User user = new User(1L, "name", "", "","", UserRole.MANAGER);
        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Order order = new Order(1L, car,150, 1.30, 2,user, true);

        when(orderRepository.findById(1L)).thenThrow( new ServiceException(400, "some exception"));
        when(orderMapper.toDTO(any())).thenCallRealMethod();


        try{
            final OrderDTO orderDTO = orderService.getOrderById(1L);
            fail();
        } catch (ServiceException e){
        }
    }


    @Test
    public void test_getOrders_doesAllOrdersExist() throws Exception{
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final List<Order> orders = getTestOrders();

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDTO(any())).thenCallRealMethod();

        final List<OrderDTO> carDTO = orderService.getAllOrders();


        long expected = orders.size();
        long res = carDTO.size();

        assertNotNull(carDTO);
        assertEquals(expected, res);
    }

    @Test
    public void test_updateOrder_orderIsUpdate() throws Exception {
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderToOrderMapper orderMapper = Mockito.mock(OrderToOrderMapper.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);

        final User user = new User(1L, "name", "", "","", UserRole.MANAGER);
        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Order order1 = new Order(1L, car,150, 1.30, 2,user, true);
        final Order order2 = new Order(1L, car,200, 1.30, 2,user, true);

        order1.setTotalPrice(200);


            when(orderRepository.save(order1)).thenReturn(any());

            final OrderDTO orderDTO = orderService.updateOrder(orderMapper.toDTO(order1));


            assertEquals(order2, orderMapper.toEntity(orderDTO));
        }

    private List<Order> getTestOrders(){

        final User user = new User(1L, "name", "", "","", UserRole.MANAGER);
        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);


        final Order order1 = new Order(1L, car,150, 1.30, 2,user, true);
        final Order order2 = new Order(1L, car,150, 1.30, 2,user, true);
        final Order order3 = new Order(1L, car,150, 1.30, 2,user, true);
        final Order order4 = new Order(1L, car,150, 1.30, 2,user, true);
        final Order order5 = new Order(1L, car,150, 1.30, 2,user, true);

        return Arrays.asList(order1, order2, order3, order4, order5);
    }
}
