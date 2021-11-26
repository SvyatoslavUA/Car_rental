package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.Car;
import lpnu.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private Car priceOfOrder;

    @NotNull
    private double spentTime;

    private double nightBonus;

    @NotNull
    @NotBlank
    private User user;
}
