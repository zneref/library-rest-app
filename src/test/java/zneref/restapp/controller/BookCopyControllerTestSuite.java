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
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.dto.BookCopyDto;
import zneref.restapp.service.BookCopyService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTestSuite {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookCopyService service;

    @Test
    public void shouldRetrieveCopiesByBookIdAndStatus() throws Exception {
        //Given
        List<BookCopyDto> bookCopyDtos = Arrays.asList(
                new BookCopyDto(1,"rented", 1)
        );
        when(service.getBookCopiesByBookIdAndStatus(anyInt(), anyString())).thenReturn(bookCopyDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/copies/items?bookId=1&status=rented").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookCopyId", is(1)))
                .andExpect(jsonPath("$[0].status", is("rented")));
    }

    @Test
    public void shouldUpdateCopy() throws Exception {
        BookCopy copy = new BookCopy(1, "available", 1);
        BookCopyDto copyDto = new BookCopyDto(1,"rented", 1);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(copy);
        when(service.addBookCopy(any(BookCopy.class))).thenReturn(copyDto);

        //When, Then
        mockMvc.perform(put("/v1/library/copies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.bookCopyId", is(1)))
                .andExpect(jsonPath("$.status", is("rented")));

    }
}