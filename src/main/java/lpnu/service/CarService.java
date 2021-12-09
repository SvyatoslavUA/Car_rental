package lpnu.service;

import lpnu.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCarsForManager();
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long id);
    CarDTO saveCar(CarDTO carDTO);
    CarDTO updateCar(CarDTO carDTO);
    void deleteCarById(Long id);
}
