package br.com.ntconsult.desafio_fairfax.service.impl;

import br.com.ntconsult.desafio_fairfax.data.repositories.ReservationRepository;
import br.com.ntconsult.desafio_fairfax.data.repositories.RoomRepository;
import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.dtos.ReservationDto;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import br.com.ntconsult.desafio_fairfax.requests.ReservationRequest;
import br.com.ntconsult.desafio_fairfax.service.NotificationService;
import br.com.ntconsult.desafio_fairfax.service.PaymentService;
import br.com.ntconsult.desafio_fairfax.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final PaymentService paymentService;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final NotificationService notificationService;

    @Override
    public ReservationDto doReservation(ReservationRequest request) {
        UUID preAuthorizePaymentUUID = paymentService.preAuthorizePayment(request.getCardNumber());

        List<Room> rooms = roomRepository.findAllById(request.getRoomsId());
        rooms.forEach(room -> room.setStatus(RoomStatus.RESERVED));

        Reservation reservation = reservationRepository.save(new Reservation(rooms, request.getName(),
                request.getContact(), request.getCheckIn(),
                request.getCheckOut(), preAuthorizePaymentUUID));

        UUID paymentUUID = paymentService.doPayment(preAuthorizePaymentUUID);
        reservation.setPaymentUUID(paymentUUID);

        notificationService.scheduleCheckInCheckOutNotifications(reservation);

        return new ReservationDto(reservationRepository.save(reservation));
    }

    @Override
    public void doCheckIn(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow();
        reservation.confirmCheckIn();
        reservationRepository.save(reservation);
    }

    @Override
    public void doCheckOut(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow();
        reservation.confirmCheckOut();
        reservationRepository.save(reservation);
    }
}
