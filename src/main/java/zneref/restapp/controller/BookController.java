package zneref.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zneref.restapp.domain.Book;
import zneref.restapp.domain.dto.BookDto;
import zneref.restapp.service.BookService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/titles")
    List<BookDto> getBooksByTitle(@RequestParam("title") final String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/authors")
    List<BookDto> getBooksByAuthor(@RequestParam("author") final String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/{id}")
    BookDto getBook(@PathVariable(value = "id") int id) throws NotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    BookDto createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

}
