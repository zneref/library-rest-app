package zneref.restapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserDto {
    private int userId;
    private String name;
    private String lastName;
    private Date created;
}
