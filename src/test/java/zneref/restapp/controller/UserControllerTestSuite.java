package zneref.restapp.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.UserDto;
import zneref.restapp.service.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService service;

    @Test
    public void shouldFetchAllUsers() throws Exception {
        //Given
        List<UserDto> userDtos = Arrays.asList(
                new UserDto(1, "name", "last name", Date.valueOf(LocalDate.of(2017, 12, 26)))
        );

        when(service.getAllUsers()).thenReturn(userDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].lastName", is("last name")))
                .andExpect(jsonPath("$[0].created", is("2017-12-26")));

    }

    @Test
    public void shouldFetchUsersByNameOrLastName() throws Exception {
        //Given
        List<UserDto> userDtos = Arrays.asList(
                new UserDto(1, "name", "last name", Date.valueOf(LocalDate.of(2017, 12, 26)))
        );

        when(service.getUsersByNameOrLastName(anyString())).thenReturn(userDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/users/all?name=name").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].lastName", is("last name")))
                .andExpect(jsonPath("$[0].created", is("2017-12-26")));
    }

    @Test
    public void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = new UserDto(1, "name", "last name", Date.valueOf(LocalDate.of(2017, 12, 26)));

        when(service.getUserById((anyInt()))).thenReturn(userDto);

        //When, Then
        mockMvc.perform(get("/v1/library/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.lastName", is("last name")))
                .andExpect(jsonPath("$.created", is("2017-12-26")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        User user = new User("name", "last name", Date.valueOf(LocalDate.of(2017, 12, 26)));
        UserDto userDto = new UserDto(2, "name2", "last name2", Date.valueOf(LocalDate.of(2017, 11, 26)));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(user);
        when(service.addUser(user)).thenReturn(userDto);

        //When, Then
        verify(service,times(1)).addUser(user);
        mockMvc.perform(post("/v1/library/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.userId", is(2)))
                .andExpect(jsonPath("$.name", is("name2")))
                .andExpect(jsonPath("$.lastName", is("last name2")))
                .andExpect(jsonPath("$.created", is("2017-11-26")));
    }
}