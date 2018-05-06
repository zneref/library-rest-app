package zneref.restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zneref.restapp.domain.Rented;
import zneref.restapp.domain.dto.RentedDto;
import zneref.restapp.mapper.RentedMapper;
import zneref.restapp.repository.RentedRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentedService {
    @Autowired
    RentedRepository rentedRepository;
    @Autowired
    RentedMapper rentedMapper;

    public RentedDto addRented(Rented rented) {
        return rentedMapper.mapToRentedDto(rentedRepository.save(rented));
    }

    public List<RentedDto> getRentedByUserAndCopy(int uId, int cId) {
        return rentedRepository.findByUserIdAndBookCopyId(uId, cId)
                .stream()
                .map(rented -> rentedMapper.mapToRentedDto(rented))
                .collect(Collectors.toList());
    }
}
