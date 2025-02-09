package br.com.ntconsult.desafio_fairfax.service.impl;

import br.com.ntconsult.desafio_fairfax.data.repositories.HotelRepository;
import br.com.ntconsult.desafio_fairfax.data.specifications.HotelSpecification;
import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.domains.Hotel_;
import br.com.ntconsult.desafio_fairfax.dtos.HotelDto;
import br.com.ntconsult.desafio_fairfax.exceptions.ResourceNotFoundException;
import br.com.ntconsult.desafio_fairfax.service.HotelService;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
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
    public HotelDto getHotel(Integer hotelId) {
        return hotelRepository.findById(hotelId)
                .map(HotelDto::new)
                .orElseThrow(() -> new ResourceNotFoundException(Hotel.class, Hotel_.ID, hotelId));
    }
}
