package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Car;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarInMemoryRepository {
    private List<Car> carList;

    private long id = 1;


    @PostConstruct
    public void init(){

        final Path file = Paths.get("car.txt");
        try {
            final String savedCarsAsString = Files.readString(file, StandardCharsets.UTF_16);
            carList = JacksonUtil.deserialize(savedCarsAsString, new TypeReference<>() {
            });
        } catch (final Exception e){
            System.out.println("We have an issue");
            carList = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("car.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(carList), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(carList);
    }

    public Car getCarById(final Long id) {
        return carList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "car with id {" + id + "} not found"));
    }

    public Car saveCar(final Car car) {
        car.setId(id);

        ++id;

        carList.add(car);
        return car;
    }

    public Car updateCar(final Car car) {

        final Car savedUser = getCarById(car.getId());

        savedUser.setCarStatus(car.getCarStatus());
        savedUser.setClassOfCar(car.getClassOfCar());
        savedUser.setTransmission(car.getTransmission());
        savedUser.setModel(car.getModel());
        savedUser.setCarColor(car.getCarColor());
        savedUser.setCarNumber(car.getCarNumber());

        return savedUser;
    }

    public void deleteUserById(final Long id) {
        carList = carList.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }
}
