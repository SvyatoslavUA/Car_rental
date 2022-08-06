package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Order;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class OrderInMemoryRepository {
    private List<Order> orders;

    private long id = 1;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("orders.txt");
        try {
            final String savedOrdersAsString = Files.readString(file, StandardCharsets.UTF_16);
            orders = JacksonUtil.deserialize(savedOrdersAsString, new TypeReference<>() {
            });
        } catch (final Exception e){
            System.out.println("We have an issue");
            orders = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("orders.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(orders), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order getOrderById(final Long id) {
        return orders.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "order with id {" + id + "} not found"));
    }

    public Order saveOrder(final Order order) {
        order.setId(id);

        ++id;

        orders.add(order);
        return order;
    }

    public Order updateOrder(final Order order) {

        final Order savedOrder = getOrderById(order.getId());

        savedOrder.setCar(order.getCar());
        savedOrder.setTotalPrice(order.getTotalPrice());
        savedOrder.setNightBonus(order.getNightBonus());
        savedOrder.setUser(order.getUser());

        return savedOrder;
    }

    public void deleteOrderById(final Long id) {
        orders = orders.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }
}
