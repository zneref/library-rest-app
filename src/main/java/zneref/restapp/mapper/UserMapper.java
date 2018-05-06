package zneref.restapp.mapper;

import org.springframework.stereotype.Component;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.UserDto;

import java.util.ArrayList;

@Component
public class UserMapper {
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getLastName(),
                user.getCreated());
    }

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getCreated()
        );
    }
}
