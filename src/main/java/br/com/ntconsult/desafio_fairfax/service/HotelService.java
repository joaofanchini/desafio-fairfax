package br.com.ntconsult.desafio_fairfax.service;

import br.com.ntconsult.desafio_fairfax.dtos.HotelDto;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface HotelService {
    Page<HotelDto> getHotels(@Nullable LocalDate dateCheckIn,
                             @Nullable LocalDate dateCheckOut,
                             @Nullable Integer numberOfRooms,
                             @Nullable Integer numberOfGuests,
                             Pageable pageable);

    HotelDto getHotelDetails(Integer hotelId);
}
