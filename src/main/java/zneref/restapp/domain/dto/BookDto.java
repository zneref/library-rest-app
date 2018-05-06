package zneref.restapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private Date publicationDate;
}
