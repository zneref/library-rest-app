package zneref.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.UserDto;
import zneref.restapp.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/all")
    List<UserDto> getUsersByNameOrLastName(@RequestParam("name") final String name) {
        return userService.getUsersByNameOrLastName(name);
    }

    @GetMapping("/{id}")
    UserDto getUser(@PathVariable(value = "id") int id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    UserDto createUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
