package br.com.ntconsult.desafio_fairfax.unit;

import br.com.ntconsult.desafio_fairfax.data.repositories.ReservationRepository;
import br.com.ntconsult.desafio_fairfax.data.repositories.RoomRepository;
import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.dtos.ReservationDto;
import br.com.ntconsult.desafio_fairfax.dtos.RoomDto;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import br.com.ntconsult.desafio_fairfax.requests.ReservationRequest;
import br.com.ntconsult.desafio_fairfax.service.NotificationService;
import br.com.ntconsult.desafio_fairfax.service.PaymentService;
import br.com.ntconsult.desafio_fairfax.service.ReservationService;
import br.com.ntconsult.desafio_fairfax.service.impl.ReservationServiceImpl;
import br.com.ntconsult.desafio_fairfax.unit.base.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class ReservationServiceTest extends AbstractTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    private ReservationService reservationService;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        reservationService = new ReservationServiceImpl(paymentService, reservationRepository,
                roomRepository, notificationService);
    }

    @Test
    void shouldDoReservation() {
        // given
        UUID preAuthorizePaymentUUID = UUID.randomUUID();
        UUID paymentUUID = UUID.randomUUID();
        Hotel hotel = new Hotel("Intercity", "São Paulo");

        when(paymentService.preAuthorizePayment(any(String.class))).thenReturn(preAuthorizePaymentUUID);
        when(paymentService.doPayment(any(UUID.class))).thenReturn(paymentUUID);
        when(roomRepository.findAllById(any())).thenAnswer(answer -> {
            List<Integer> ids = answer.getArgument(0);
            return ids.stream()
                    .map(id -> {
                        Room room = new Room(hotel, 2, 200.0 * (id / 100 + 1), RoomStatus.AVAILABLE);
                        room.setId(id);
                        return room;
                    })
                    .toList();
        });
        when(reservationRepository.save(any(Reservation.class))).then(answer -> {
            Reservation reservationSaved = answer.getArgument(0, Reservation.class);
            if (Objects.isNull(reservationSaved.getId())) {
                reservationSaved.setId((int) (Math.random() * 100) + 1);
            }
            return reservationSaved;
        });

        doNothing().when(notificationService).scheduleCheckInCheckOutNotifications(any(Reservation.class));

        // when
        ReservationDto dto = reservationService.doReservation(new ReservationRequest(List.of(1, 2), "Fabio", "119810213123",
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "12312312312313123123"));

        // then
        Assertions.assertThat(dto.getId()).isNotNull();
        Assertions.assertThat(dto.getRooms()).hasSize(2);
        Assertions.assertThat(dto.getRooms().stream().map(RoomDto::getStatus).toList()).containsOnly(RoomStatus.RESERVED);
        Assertions.assertThat(dto.getConfirmCheckIn()).isFalse();
        Assertions.assertThat(dto.getConfirmCheckOut()).isFalse();
        verify(notificationService, times(1)).scheduleCheckInCheckOutNotifications(any(Reservation.class));
    }

    @Test
    void shouldDoCheckIn() {
    }

    @Test
    void shouldDoCheckOut() {
    }
}
