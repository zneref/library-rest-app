package zneref.restapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyDto {
    private int bookCopyId;
    private String status;
    private int bookId;
}
