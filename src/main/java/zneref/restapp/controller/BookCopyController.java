package zneref.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.dto.BookCopyDto;
import zneref.restapp.service.BookCopyService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/copies")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping
    List<BookCopyDto> getCopies() {
        return bookCopyService.getAllBookCopies();
    }

    @GetMapping("/{id}")
    BookCopy getCopiesByCopyId(@PathVariable(value = "id") int id) throws NotFoundException {
        return bookCopyService.getBookCopyById(id).orElseThrow(() -> new NotFoundException("Book copy not found!"));
    }

    @GetMapping("/items")
    List<BookCopyDto> getCopiesByBookIdAndStatus(
            @RequestParam("bookId") final int bookId,
            @RequestParam("status") final String status
    ) {
        return bookCopyService.getBookCopiesByBookIdAndStatus(bookId, status);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    BookCopyDto createCopy(@RequestBody BookCopy bookCopy) {
        return bookCopyService.addBookCopy(bookCopy);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    BookCopyDto updateCopy(@RequestBody BookCopy bookCopy) {
        return bookCopyService.addBookCopy(bookCopy);
    }
}
