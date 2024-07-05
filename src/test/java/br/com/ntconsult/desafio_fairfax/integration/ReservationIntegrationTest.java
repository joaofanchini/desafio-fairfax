package br.com.ntconsult.desafio_fairfax.integration;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.dtos.ReservationDto;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import br.com.ntconsult.desafio_fairfax.integration.base.AbstractIntegrationTest;
import br.com.ntconsult.desafio_fairfax.requests.ReservationRequest;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
class ReservationIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDoReservation() throws Exception {
        // given
        Hotel hotel = hotelRepository.saveAndFlush(new Hotel("Hotel Teste", "cidade"));
        roomRepository.saveAndFlush(new Room(hotel, 4, 200.0, RoomStatus.AVAILABLE));

        // when
        var request = new ReservationRequest() {{
            setRoomsId(List.of(1));
            setName("João");
            setContact("11988877878");
            setCardNumber("121312323123123");
            setCheckIn(LocalDate.now().plusDays(2));
            setCheckOut(LocalDate.now().plusDays(9));
        }};

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/reservations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        MvcResult mvcResult = result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andReturn();

        ReservationDto dto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReservationDto.class);
        Reservation reservation = reservationRepository.findById(dto.getId())
                .orElseThrow();

        Assertions.assertThat(reservation.getPaymentUUID()).isNotNull();
        Assertions.assertThat(reservation.getPreAuthorizePaymentUUID()).isNotNull();
        Assertions.assertThat(reservation.getName()).isNotNull();
        Assertions.assertThat(reservation.getCheckIn()).isNotNull();
        Assertions.assertThat(reservation.getCheckOut()).isNotNull();
        Assertions.assertThat(reservation.getConfirmCheckIn()).isFalse();
        Assertions.assertThat(reservation.getConfirmCheckOut()).isFalse();
        Assertions.assertThat(reservation.getRooms().stream().map(Room::getStatus).toList()).containsOnly(RoomStatus.RESERVED);

    }

}
