package lpnu.service.impl;

import lpnu.dto.CarDTO;
import lpnu.entity.Car;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;
import lpnu.exception.ServiceException;
import lpnu.mapper.CarToCarMapperDTO;
import lpnu.repository.CarRepository;
import lpnu.service.CarService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CarServiceImplTest {
    @Test
    public void test_getCarById_carExist() throws Exception{
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);

        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);


        when(сarRepository.getCarById(1L)).thenReturn(car);
        when(carMapper.toDTO(any())).thenCallRealMethod();


        final CarDTO carDTO = сarService.getCarById(1L);

        assertNotNull(carDTO);
        assertEquals(1L, (long)carDTO.getId());
        assertEquals(car.getCarNumber(), carDTO.getCarNumber());
    }

    @Test
    public void test_getCarById_carNotExist() throws Exception {
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);

        final Car car = new Car(1L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);


        when(сarRepository.getCarById(1L)).thenThrow(new ServiceException(400, "some exception"));
        when(carMapper.toDTO(any())).thenCallRealMethod();


        try {
            final CarDTO carDTO = сarService.getCarById(1L);
            fail();
        } catch (ServiceException e) {
        }
    }


    @Test
    public void test_getCars_doesAllCarsExist() throws Exception{
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);

        final List<Car> cars = getTestCars();

        when(сarRepository.getAllCars()).thenReturn(cars);
        when(carMapper.toDTO(any())).thenCallRealMethod();

        final List<CarDTO> carDTO = сarService.getAllCarsForManager();


        long expected = cars.size();
        long res = carDTO.size();

        assertNotNull(carDTO);
        assertEquals(expected, res);
    }

    @Test
    public void test_updateCar_carIsUpdate() throws Exception {
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);

        final Car car1 = new Car(1L, "t", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Car car2 = new Car(1L, "s", "",  "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);


        when(сarRepository.updateCar(car1)).thenReturn(car2);
        when(carMapper.toDTO(any())).thenCallRealMethod();

        final CarDTO carDTO = сarService.updateCar(carMapper.toDTO(car1));

        assertEquals(car2, carDTO);
    }

    private List<Car> getTestCars(){
        final Car car1 = new Car(1L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Car car2 = new Car(2L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Car car3 = new Car(3L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);
        final Car car4 = new Car(4L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);

        return Arrays.asList(car1, car2, car3, car4);
    }
}

