package br.com.ntconsult.desafio_fairfax.service.impl;

import br.com.ntconsult.desafio_fairfax.data.repositories.HotelRepository;
import br.com.ntconsult.desafio_fairfax.data.specifications.HotelSpecification;
import br.com.ntconsult.desafio_fairfax.dtos.HotelDto;
import br.com.ntconsult.desafio_fairfax.service.HotelService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Page<HotelDto> getHotels(@Nullable LocalDate dateCheckIn,
                                    @Nullable LocalDate dateCheckOut,
                                    @Nullable Integer numberOfRooms,
                                    @Nullable Integer numberOfGuests,
                                    Pageable pageable) {
        return hotelRepository.findAll(HotelSpecification.advancedFilter(dateCheckIn, dateCheckOut, numberOfRooms, numberOfGuests), pageable)
                .map(HotelDto::new);
    }

    @Override
    public HotelDto getHotelDetails(Integer hotelId) {
        return hotelRepository.findById(hotelId)
                .map(HotelDto::new)
                .orElseThrow();
    }
}
