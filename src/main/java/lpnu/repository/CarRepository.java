package lpnu.repository;

import lpnu.entity.Car;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select ud from car")
    List<Car> getAllCars();

    @Query("select ud from car where ud.carID=:carID")
    List<Car> getCarById(Long carID);

    @Modifying
    @Query(value = "INSERT INTO car(carID, model, carNumber, carColor, carCapacity, classOfCar, transmission, carStatus)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    void addCar(Long carID, String model, String carNumber, String carColor, Integer carCapacity,
                             CarClass classOfCar, CarTransmission transmission, CarStatus carStatus);


    @Modifying
    @Query("DELETE FROM car WHERE carID=?1")
    void deleteCar(Long carID);
}
