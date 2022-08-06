package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
@Entity(name = "order")
public class Order {
    @Id
    @Column(name = "orderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "car")
    private Car car;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "spentTime")
    private double spentTime;

    @Column(name = "nightBonus")
    private double nightBonus;

    @Column(name = "user")
    private User user;

    @Column(name = "isActive")
    private boolean isActive;
}
