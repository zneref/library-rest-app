package zneref.restapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zneref.restapp.controller.NotFoundException;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.UserDto;
import zneref.restapp.mapper.UserMapper;
import zneref.restapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByNameOrLastName(final String name) {
        return userRepository.findByNameOrLastName(name)
                .stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(final int userId) {
        return userRepository.findByUserId(userId);
    }

    public UserDto addUser(User user) {
        return userMapper.mapToUserDto(userRepository.save(user));
    }

}
