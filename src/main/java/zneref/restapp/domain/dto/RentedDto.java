package zneref.restapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.User;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentedDto {
    private int rentedId;
    private Date rentedFrom;
    private Date rentedTo;
    private int bookCopyId;
    private int userId;
}
