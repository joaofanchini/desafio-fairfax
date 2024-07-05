package br.com.ntconsult.desafio_fairfax.unit;

import br.com.ntconsult.desafio_fairfax.data.repositories.HotelRepository;
import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.dtos.HotelDto;
import br.com.ntconsult.desafio_fairfax.exceptions.ResourceNotFoundException;
import br.com.ntconsult.desafio_fairfax.service.HotelService;
import br.com.ntconsult.desafio_fairfax.service.impl.HotelServiceImpl;
import br.com.ntconsult.desafio_fairfax.unit.base.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class HotelServiceTest extends AbstractTest {

    @Mock
    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        hotelService = new HotelServiceImpl(hotelRepository);
    }

    @Test
    void shouldGetHotelDetails() {
        // given

        int hotelId = 1;
        Hotel hotelMock = new Hotel("Intercity", "São Paulo");
        hotelMock.setId(hotelId);
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotelMock));

        // when
        HotelDto dto = hotelService.getHotel(hotelId);

        // then
        Assertions.assertThat(dto.getId()).isEqualTo(hotelMock.getId());
        Assertions.assertThat(dto.getName()).isEqualTo(hotelMock.getName());

        verify(hotelRepository, times(1)).findById(hotelId);
    }

    @Test
    void shouldGetAnErrorWhenNotFoundTheHotel() {
        // given
        int hotelId = 1;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // when
        ResourceNotFoundException resourceNotFoundException = Assertions.catchThrowableOfType(() -> hotelService.getHotel(hotelId), ResourceNotFoundException.class);

        // then
        Assertions.assertThat(resourceNotFoundException).isNotNull();
        verify(hotelRepository, times(1)).findById(hotelId);
    }
}
