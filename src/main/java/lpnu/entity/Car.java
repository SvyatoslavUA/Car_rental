package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
@Entity(name = "car")
public class Car {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "carNumber")
    private String carNumber;

    @Column(name = "carColor")
    private String carColor;

    @Column(name = "carCapacity")
    private int carCapacity;

    @Column(name = "classOfCar")
    private CarClass classOfCar;

    @Column(name = "transmission")
    private CarTransmission transmission;

    @Column(name = "carStatus")
    private CarStatus carStatus;

    @OneToMany(mappedBy="car")
    private List<Order> orders;
}
