package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private CarDTO car;

    @Min(1)
    private double totalPrice;

    private boolean isActive;


    @Min(1)
    private double spentTime;

    @Min(1)
    private double nightBonus;

    @NotNull
    @NotBlank
    private UserDTO user;
}
