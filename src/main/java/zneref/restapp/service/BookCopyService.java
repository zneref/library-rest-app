package zneref.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zneref.restapp.controller.NotFoundException;
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.dto.BookCopyDto;
import zneref.restapp.mapper.BookCopyMapper;
import zneref.restapp.repository.BookCopyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository repository;
    @Autowired
    private BookCopyMapper mapper;

    public List<BookCopyDto> getAllBookCopies() {
        return repository.findAll()
                .stream()
                .map(book -> mapper.mapToBookCopyDto(book))
                .collect(Collectors.toList());
    }

    public List<BookCopyDto> getBookCopiesByBookIdAndStatus(int bookId, String status) {
        return repository.findByBookIdAndStatus(bookId, status)
                .stream()
                .map(book -> mapper.mapToBookCopyDto(book))
                .collect(Collectors.toList());
    }

    public BookCopyDto getBookCopyById(int id) throws NotFoundException {
        return mapper.mapToBookCopyDto(repository.findByBookCopyId(id).orElseThrow(NotFoundException::new));
    }

    public BookCopyDto addBookCopy(BookCopy copy) {
        return mapper.mapToBookCopyDto(repository.save(copy));
    }
}
