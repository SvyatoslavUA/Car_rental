package lpnu.service;

import lpnu.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCarsForManager();
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long id);
    CarDTO saveCar(CarDTO fuelDTO);
    CarDTO updateCar(CarDTO fuelDTO);
    void deleteCarById(Long id);
}
