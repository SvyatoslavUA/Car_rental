package lpnu.service;

import lpnu.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO getCarById(long id);
    CarDTO saveCar(CarDTO fuelDTO);
    CarDTO updateCar(CarDTO fuelDTO);
    void deleteCarById(long id);


}
