package zneref.restapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import zneref.restapp.domain.dto.BookDto;
import zneref.restapp.service.BookService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTestSuite {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService service;

    @Test
    public void shouldFetchAllBooks() throws Exception{
        //Given
        List<BookDto> bookDtos = Arrays.asList(
              new BookDto(1, "title", "author", Date.valueOf(LocalDate.of(2004, 10, 20)))
        );
        when(service.getAllBooks()).thenReturn(bookDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].publicationDate", is("2004-10-20")));
    }

    @Test
    public void ShouldFechBooksByTitle() throws Exception {
        //Given
        List<BookDto> bookDtos = Arrays.asList(
                new BookDto(1, "title", "author", Date.valueOf(LocalDate.of(2004, 10, 20)))
        );
        when(service.getBooksByTitle(anyString())).thenReturn(bookDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/books/titles?title=title").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].publicationDate", is("2004-10-20")));
    }

    @Test
    public void getBooksByAuthor() throws Exception {
        //Given
        List<BookDto> bookDtos = Arrays.asList(
                new BookDto(1, "title", "author", Date.valueOf(LocalDate.of(2004, 10, 20)))
        );
        when(service.getBooksByAuthor(anyString())).thenReturn(bookDtos);

        //When, Then
        mockMvc.perform(get("/v1/library/books/authors?author=author").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].publicationDate", is("2004-10-20")));
    }
}