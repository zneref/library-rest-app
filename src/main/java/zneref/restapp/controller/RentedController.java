package zneref.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zneref.restapp.domain.Rented;
import zneref.restapp.domain.dto.RentedDto;
import zneref.restapp.service.RentedService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/rented")
public class RentedController {
    @Autowired
    RentedService rentedService;

    @GetMapping("/items")
    List<RentedDto> getRentedListByUserAndCopy(@RequestParam("userId") final int uId,
                                           @RequestParam("copyId") final int cId) {
        return rentedService.getRentedByUserAndCopy(uId, cId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    RentedDto createRented(@RequestBody Rented rented) {
        return rentedService.addRented(rented);
    }
}
