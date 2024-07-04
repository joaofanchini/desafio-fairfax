package br.com.ntconsult.desafio_fairfax.controllers;

import br.com.ntconsult.desafio_fairfax.dtos.HotelDto;
import br.com.ntconsult.desafio_fairfax.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public Page<HotelDto> getHotels(@RequestParam(required = false) LocalDate dateCheckIn,
                                    @RequestParam(required = false) LocalDate dateCheckOut,
                                    @RequestParam(required = false) Integer numberOfRooms,
                                    @RequestParam(required = false) Integer numberOfGuests,
                                    Pageable pageable) {
        return hotelService.getHotels(dateCheckIn, dateCheckOut, numberOfRooms, numberOfGuests, pageable);
    }

    @GetMapping("/{hotelId}")
    public HotelDto getHotelDetails(@PathVariable Integer hotelId) {
        return hotelService.getHotelDetails(hotelId);
    }

}
