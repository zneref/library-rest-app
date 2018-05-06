package zneref.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zneref.restapp.controller.NotFoundException;
import zneref.restapp.domain.Book;
import zneref.restapp.domain.dto.BookDto;
import zneref.restapp.mapper.BookMapper;
import zneref.restapp.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> bookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

     public List<BookDto> getBooksByTitle(String title) {
        return  bookRepository.findByTitleContaining(title)
                .stream()
                .map(book -> bookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByAuthor(String author) {
        return  bookRepository.findByAuthorContaining(author)
                .stream()
                .map(book -> bookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public BookDto getBookById(int id) throws NotFoundException {
        return bookMapper.mapToBookDto(bookRepository.findByBookId(id).orElseThrow(NotFoundException::new));
    }

    public BookDto addBook(Book book) {
        return bookMapper.mapToBookDto(bookRepository.save(book));
    }
}
