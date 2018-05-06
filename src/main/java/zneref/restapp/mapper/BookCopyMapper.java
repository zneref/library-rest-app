package zneref.restapp.mapper;

import org.springframework.stereotype.Component;
import zneref.restapp.domain.Book;
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.dto.BookCopyDto;

import java.util.ArrayList;

@Component
public class BookCopyMapper {
    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getBookCopyId(),
                bookCopy.getStatus(),
                bookCopy.getBookId()
        );
    }

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getBookCopyId(),
                bookCopyDto.getStatus(),
                bookCopyDto.getBookId()
        );
    }
}
