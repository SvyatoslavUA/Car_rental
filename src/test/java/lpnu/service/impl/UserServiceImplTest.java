package lpnu.service.impl;


import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.entity.enumeration.UserRole;
import lpnu.exception.ServiceException;
import lpnu.mapper.UserToUserMapperDTO;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
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

        when(userRepository.findById(1L)).thenThrow( new ServiceException(400, "some exception"));
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

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDTO(any())).thenCallRealMethod();

        final List<UserDTO> userDTO = userService.getAllUsers();


        long expected = users.size();
        long res = userDTO.size();

        assertNotNull(userDTO);
        assertEquals(expected, res);
    }

    @Test
    public void test_updateUser_userIsChanged() throws Exception {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final UserToUserMapperDTO userMapper = Mockito.mock(UserToUserMapperDTO.class);

        final UserService userService = new UserServiceImpl(userMapper, userRepository);


        final User user1 = new User(1L, "name1", "", "","", UserRole.MANAGER);
        final User user2 = new User(1L, "name2", "", "","", UserRole.MANAGER);

        user1.setName("name2");


        when(userRepository.save(user1)).thenReturn(any());

        final UserDTO userDTO = userService.updateUser(userMapper.toDTO(user1));


        assertEquals(user2, userMapper.toEntity(userDTO));
    }

    private List<User> getTestUsers(){
        final User user1 = new User(1L, "name", "", "","", UserRole.MANAGER);
        final User user2 = new User(2L, "name", "", "","", UserRole.MANAGER);
        final User user3 = new User(3L, "name", "", "","", UserRole.MANAGER);
        final User user4 = new User(4L, "name", "", "","", UserRole.MANAGER);

        return Arrays.asList(user1, user2, user3, user4);
    }
}
