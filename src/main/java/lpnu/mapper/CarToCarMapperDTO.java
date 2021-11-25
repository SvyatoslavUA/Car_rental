package lpnu.mapper;

import lpnu.dto.CarDTO;
import lpnu.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarToCarMapperDTO {
    public Car toEntity(final CarDTO carDTO){
        final Car car = new Car();

        car.setId(carDTO.getId());
        car.setCarColor(carDTO.getCarColor());
        car.setCarNumber(carDTO.getCarNumber());
        car.setModel(carDTO.getModel());
        car.setCarCapacity(carDTO.getCarCapacity());
        car.setClassOfCar(carDTO.getClassOfCar());
        car.setTransmission(carDTO.getTransmission());

        return car;
    }

    public CarDTO toDTO(final Car car){
        final CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setCarColor(car.getCarColor());
        carDTO.setCarNumber(car.getCarNumber());
        carDTO.setModel(car.getModel());
        carDTO.setCarCapacity(car.getCarCapacity());
        carDTO.setClassOfCar(car.getClassOfCar());
        carDTO.setTransmission(car.getTransmission());

        return carDTO;
    }
}
