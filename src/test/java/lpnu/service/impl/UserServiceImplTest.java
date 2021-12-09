package lpnu.service.impl;


import lpnu.dto.CarDTO;
import lpnu.dto.OrderDTO;
import lpnu.dto.UserDTO;
import lpnu.entity.Car;
import lpnu.entity.Order;
import lpnu.entity.User;
import lpnu.entity.enumeration.CarClass;
import lpnu.entity.enumeration.CarStatus;
import lpnu.entity.enumeration.CarTransmission;
import lpnu.entity.enumeration.UserRole;
import lpnu.exception.ServiceException;
import lpnu.mapper.CarToCarMapperDTO;
import lpnu.mapper.OrderToOrderMapper;
import lpnu.mapper.UserToUserMapperDTO;
import lpnu.repository.CarRepository;
import lpnu.repository.OrderRepository;
import lpnu.repository.UserRepository;
import lpnu.service.CarService;
import lpnu.service.OrderService;
import lpnu.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Test
    public void test_getUserById_userExist() throws Exception{
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserMapperDTO userMapper = Mockito.mock(UserToUserMapperDTO.class);

        final UserService userService = new UserServiceImpl(userMapper, userRepository);


        final User user = new User(1L, "name", "", "","", UserRole.MANAGER);

        when(userRepository.getUserById(1L)).thenReturn(user);
        when(userMapper.toDTO(any())).thenCallRealMethod();


        final UserDTO userDTO = userService.getUserById(1);

        assertNotNull(userDTO);
        assertEquals(1L, (long)userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    public void test_getUserById_userNotExist() throws Exception{
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserMapperDTO userMapper = Mockito.mock(UserToUserMapperDTO.class);


        final UserService userService = new UserServiceImpl(userMapper, userRepository);

        when(userRepository.getUserById(1L)).thenThrow( new ServiceException(400, "some exception"));
        when(userMapper.toDTO(any())).thenCallRealMethod();


        try{
            final UserDTO userDTO = userService.getUserById(1);
            fail();
        } catch (ServiceException e){

        }
    }

    @Test
    public void test_getOrders_doesAllUsersExist() throws Exception{
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserMapperDTO userMapper = Mockito.mock(UserToUserMapperDTO.class);

        final UserService userService = new UserServiceImpl(userMapper, userRepository);


        final List<User> users = getTestUsers();

        when(userRepository.getAllUsers()).thenReturn(users);
        when(userMapper.toDTO(any())).thenCallRealMethod();

        final List<UserDTO> userDTO = userService.getAllUsers();


        long expected = users.size();
        long res = userDTO.size();

        assertNotNull(userDTO);
        assertEquals(expected, res);
    }

    @Test
    public void test_updateUser_userIsUpdate() throws Exception {
        final CarRepository сarRepository = Mockito.mock(CarRepository.class);
        final CarToCarMapperDTO carMapper = Mockito.mock(CarToCarMapperDTO.class);

        final CarService сarService = new CarServiceImpl(carMapper, сarRepository);

        final Car car1 = new Car(1L, "", "", "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);

        final CarDTO car2 = new CarDTO(1L, "", "",  "", 4, CarClass.COMFORT, CarTransmission.MANUAL, CarStatus.ACTIVE);


        when(сarRepository.updateCar(car1)).thenReturn(car1);
        when(carMapper.toDTO(any())).thenCallRealMethod();


        assertEquals(car2, car1);
    }

    private List<User> getTestUsers(){
        final User user1 = new User(1L, "name", "", "","", UserRole.MANAGER);
        final User user2 = new User(2L, "name", "", "","", UserRole.MANAGER);
        final User user3 = new User(3L, "name", "", "","", UserRole.MANAGER);
        final User user4 = new User(4L, "name", "", "","", UserRole.MANAGER);

        return Arrays.asList(user1, user2, user3, user4);
    }
}
