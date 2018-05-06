package zneref.restapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import zneref.restapp.domain.dto.RentedDto;
import zneref.restapp.service.RentedService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentedController.class)
public class RentedControllerTestSuite {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RentedService service;

    @Test
    public void shouldRetrieveRentedListByUserAndCopy() throws Exception {
        //Given
        List<RentedDto> rentedDtos = Arrays.asList(new RentedDto(1,
                Date.valueOf(LocalDate.of(2018, 1, 20)),
                Date.valueOf(LocalDate.of(2004, 2, 20)),
                1,
                1));

        when(service.getRentedByUserAndCopy(anyInt(), anyInt())).thenReturn(rentedDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/rented/items?userId=1&copyId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rentedId", is(1)));
    }
}