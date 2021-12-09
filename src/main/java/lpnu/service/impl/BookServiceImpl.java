package lpnu.service.impl;


import lpnu.dto.OrderDTO;
import lpnu.entity.Car;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.entity.enumeration.CarStatus;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.repository.CarRepository;
import lpnu.repository.OrderRepository;
import lpnu.repository.UserRepository;
import lpnu.service.BookService;
import lpnu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OrderToOrderMapper orderToOrderMapper;
    @Autowired
    private EmailService emailService;

    @Override
    public OrderDTO bookCar(Long userId, Long carId) {
        final Car car = carRepository.getCarById(carId);
        car.setCarStatus(CarStatus.NOT_ACTIVE);
        Order order = new Order();
        order.setCar(car);

        final User user = userRepository.getUserById(userId);
        order.setUser(user);
        order.setActive(true);

        emailService.sendSimpleMessage(user.getEmail(), "Car is booked", "You booked: " + car.toString());

        return orderToOrderMapper.toDTO(order);
    }

    @Override
    public void cancelBook(Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        order.setActive(false);
        emailService.sendSimpleMessage(order.getUser().getEmail(), "Car booking", "Your order " + orderId + " canceled");
        orderRepository.updateOrder(order);
    }

}
