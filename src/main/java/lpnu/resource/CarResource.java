package lpnu.resource;

import lpnu.dto.CarDTO;
import lpnu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarResource {
    @Autowired
    private CarService carService;

    @GetMapping("/car-manager")
    public List<CarDTO> getAllCarsForManager(){return carService.getAllCarsForManager();}

    @GetMapping("/car")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public CarDTO getCarById(@PathVariable final Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/car")
    public CarDTO saveCar(@RequestBody @Validated final CarDTO carDTO) {
        return carService.saveCar(carDTO);
    }

    @PutMapping("/car")
    public CarDTO updateCar(@RequestBody @Validated final CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteCarById(@PathVariable final Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.ok().build();
    }
}
