package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private Car priceOfOrder;
    private double spentTime;
    private double nightBonus;
    private User user;
}