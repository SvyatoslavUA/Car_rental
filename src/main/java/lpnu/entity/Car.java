package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;
    private String model;
    private String carNumber;
    private String carColor;
    private int carCapacity;
    private CarClass classOfCar;
    private CarTransmission transmission;
    private CarStatus carStatus;
}
