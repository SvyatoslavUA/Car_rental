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
@Table(name = "cars")
@Entity(name = "cars")
public class Car {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "car_color")
    private String carColor;

    @Column(name = "car_capacity")
    private int carCapacity;

    @Column(name = "class_of_car")
    private CarClass classOfCar;

    @Column(name = "transmission")
    private CarTransmission transmission;

    @Column(name = "car_status")
    private CarStatus carStatus;

    @OneToMany(mappedBy="car")
    private List<Order> orders;
}
