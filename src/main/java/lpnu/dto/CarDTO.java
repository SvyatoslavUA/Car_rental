package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarTransmission;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;

    @NotBlank
    @NotNull
    private String model;

    @Max(8)
    @Min(8)
    private String carNumber;

    @NotBlank
    @NotNull
    private String carColor;

    @Max(4)
    @Min(2)
    private int carCapacity;

    @NotNull
    private CarClass classOfCar;

    @NotNull
    private CarTransmission transmission;
}
