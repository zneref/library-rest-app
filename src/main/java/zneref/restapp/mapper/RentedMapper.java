package zneref.restapp.mapper;

import org.springframework.stereotype.Component;
import zneref.restapp.domain.BookCopy;
import zneref.restapp.domain.Rented;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.RentedDto;

@Component
public class RentedMapper {
    public RentedDto mapToRentedDto(final Rented rented) {
        return new RentedDto(
                rented.getRentedId(),
                rented.getRentedFrom(),
                rented.getRentedTo(),
                rented.getBookCopyId(),
                rented.getUserId());
    }

    public Rented mapToRented(final RentedDto rentedDto) {
        return new Rented(
                rentedDto.getRentedId(),
                rentedDto.getRentedFrom(),
                rentedDto.getRentedTo(),
                rentedDto.getBookCopyId(),
                rentedDto.getUserId()
        );
    }
}
