package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity(name = "orders")
public class Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Car car;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "spent_time")
    private double spentTime;

    @Column(name = "night_bonus")
    private double nightBonus;

    @ManyToOne
    private User user;

    @Column(name = "is_active")
    private boolean isActive;
}
