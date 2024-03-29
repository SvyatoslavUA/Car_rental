package lpnu.service.impl;

import lpnu.dto.CarDTO;
import lpnu.dto.OrderDTO;
import lpnu.entity.Car;
import lpnu.entity.Order;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;
import lpnu.exception.ServiceException;
import lpnu.mapper.CarToCarMapperDTO;
import lpnu.repository.CarRepository;
import lpnu.repository.CarRepository;
import lpnu.service.CarService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {
    @Test
    public void test_getCarById_carExist() throws Exception{
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);
        final List<Order> orders = new ArrayList<>();

        final Car car = new Car(1L,"","","",4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);


        when(сarRepository.findById(1L)).thenCallRealMethod();;
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
        final List<Order> orders = new ArrayList<>();
        final Car car = new Car(1L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);


        when(сarRepository.findById(1L)).thenThrow(new ServiceException(400, "some exception"));
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

        when(сarRepository.findAll()).thenReturn(cars);
        when(carMapper.toDTO(any())).thenCallRealMethod();

        final List<CarDTO> carDTO = сarService.getAllCarsForManager();


        long expected = cars.size();
        long res = carDTO.size();

        assertNotNull(carDTO);
        assertNotNull(cars);
        assertEquals(expected, res);
    }

    @Test
    public void test_updateCar_carIsChanged() throws Exception {
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
       final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

       final CarService сarService = new CarServiceImpl(carMapper, сarRepository);
        final List<Order> orders = new ArrayList<>();
       final   Car car1 = new Car(1L, "t", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);
        final Car car2 = new Car(1L, "s", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);


        car1.setModel("s");


        when(сarRepository.save(car1)).thenReturn(any());

        final CarDTO carDTO = сarService.updateCar(carMapper.toDTO(car1));


        assertEquals(car2, carMapper.toEntity(carDTO));
    }

    private List<Car> getTestCars(){
        final List<Order> orders = new ArrayList<>();
        final Car car1 = new Car(1L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);
        final Car car2 = new Car(2L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);
        final Car car3 = new Car(3L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);
        final Car car4 = new Car(4L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE, orders);

        return Arrays.asList(car1, car2, car3, car4);
    }
}

