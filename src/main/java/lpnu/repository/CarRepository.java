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

//    @Query(value = "select * from car", nativeQuery = true)
//    List<Car> getAllCars();
//
//    @Query("select u from car u where u.id=:ID")
//    List<Car> getCarById(Long ID);
//
//    @Modifying
//    @Query(value = "INSERT INTO car(id, model, carNumber, carColor, carCapacity, classOfCar, transmission, carStatus)"
//            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
//    void addCar(Long ID, String model, String carNumber, String carColor, Integer carCapacity,
//                             CarClass classOfCar, CarTransmission transmission, CarStatus carStatus);
//
//
//    @Modifying
//    @Query("DELETE FROM car WHERE ID=?1")
//    void deleteCar(Long ID);
}
