package zneref.restapp.mapper;

import org.springframework.stereotype.Component;
import zneref.restapp.domain.Book;
import zneref.restapp.domain.dto.BookDto;

import java.util.ArrayList;

@Component
public class BookMapper {
    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationDate()
        );
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getBookId(),
                bookDto.getTitle(),
                bookDto.getTitle(),
                bookDto.getPublicationDate()
        );
    }
}
