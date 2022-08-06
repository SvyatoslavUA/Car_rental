package lpnu.service.impl;

import lpnu.dto.UserDTO;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.mapper.UserToUserMapperDTO;
import lpnu.repository.UserRepository;
import lpnu.repository.UserInMemoryRepository;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserToUserMapperDTO userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(final UserToUserMapperDTO userMapper, final UserInMemoryRepository userInMemoryRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(final long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new ServiceException(400, "User with id not found: " + id, "")));
    }

    @Override
    public UserDTO saveUser(final UserDTO userDTO) {

        final User user = userMapper.toEntity(userDTO);

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(final UserDTO userDTO) {
        if(userDTO.getId() == null){
          //  throw new

        }
        final User savedUser = userRepository.findById(userDTO.getId()).orElseThrow(() -> new ServiceException(400, "Car with id not found: " + userDTO.getId(), ""));

        final User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUserById(final long id) {
        userRepository.deleteById(id);
    }
}
