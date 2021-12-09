package lpnu.service.impl;

import lpnu.entity.enumeration.CarStatus;
import lpnu.mapper.UserToUserMapperDTO;
import lpnu.repository.UserRepository;
import lpnu.service.CarService;
import lpnu.dto.CarDTO;
import lpnu.entity.Car;
import lpnu.mapper.CarToCarMapperDTO;
import lpnu.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarToCarMapperDTO carMapper;

    @Autowired
    private CarRepository carRepository;

    public CarServiceImpl(final CarToCarMapperDTO carMapper, final CarRepository carRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDTO> getAllCarsForManager() {
        return carRepository.getAllCars().stream()
                .map(e -> carMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.getAllCars().stream()
                .filter(e -> e.getCarStatus() == CarStatus.ACTIVE)
                .map(e -> carMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(final Long id) {
        return carMapper.toDTO(carRepository.getCarById(id));
    }

    @Override
    public CarDTO saveCar(final CarDTO userDTO) {

        final Car car = carMapper.toEntity(userDTO);
        carRepository.saveCar(car);
        return carMapper.toDTO(car);
    }

    @Override
    public CarDTO updateCar(final CarDTO carDTO) {

        final Car car = carMapper.toEntity(carDTO);
        return carMapper.toDTO(carRepository.updateCar(car));
    }

    @Override
    public void deleteCarById(final Long id) {
        carRepository.deleteUserById(id);
    }

}
