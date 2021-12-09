package lpnu.service.impl;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.mapper.UserToUserMapperDTO;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserToUserMapperDTO userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(final UserToUserMapperDTO userMapper, final UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(e -> userMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(final long id) {
        return userMapper.toDTO(userRepository.getUserById(id));
    }

    @Override
    public UserDTO saveUser(final UserDTO userDTO) {

        final User user = userMapper.toEntity(userDTO);

        userRepository.saveUser(user);

        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(final UserDTO userDTO) {

        final User user = userMapper.toEntity(userDTO);

        return userMapper.toDTO(userRepository.updateUser(user));
    }

    @Override
    public void deleteUserById(final long id) {
        userRepository.deleteUserById(id);
    }
}
