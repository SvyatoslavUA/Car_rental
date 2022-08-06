package lpnu.service.impl;

import lpnu.entity.enumeration.CarStatus;
import lpnu.exception.ServiceException;
import lpnu.repository.CarRepository;
import lpnu.service.CarService;
import lpnu.dto.CarDTO;
import lpnu.entity.Car;
import lpnu.mapper.CarToCarMapperDTO;
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
        return carRepository.findAll().stream()
                .map(e -> carMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .filter(e -> e.getCarStatus() == CarStatus.ACTIVE)
                .map(e -> carMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(final Long id) {
        return carMapper.toDTO(carRepository.findById(id).orElseThrow(() -> new ServiceException(400, "User with id not found: " + id, "")));
    }

    @Override
    public CarDTO saveCar(final CarDTO userDTO) {

        final Car car = carMapper.toEntity(userDTO);
        carRepository.save(car);
        return carMapper.toDTO(car);
    }

    @Override
    public CarDTO updateCar(final CarDTO carDTO) {
        if(carDTO.getId() == null){
         //   throw new

        }
        final Car savedCar = carRepository.findById(carDTO.getId()).orElseThrow(() -> new ServiceException(400, "Car with id not found: " + carDTO.getId(), ""));

        final Car car = carMapper.toEntity(carDTO);
        return carMapper.toDTO(carRepository.save(car));
    }

    @Override
    public void deleteCarById(final Long id) {
        carRepository.deleteById(id);
    }

}
