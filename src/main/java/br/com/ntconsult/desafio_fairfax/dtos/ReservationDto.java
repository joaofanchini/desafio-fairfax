package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class ReservationDto {
    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.name = reservation.getName();
        this.contact = reservation.getContact();
        this.rooms = Optional.ofNullable(reservation.getRooms()).orElse(new ArrayList<>())
                .stream()
                .map(RoomDto::new)
                .toList();
        this.checkIn = reservation.getCheckIn();
        this.checkOut = reservation.getCheckOut();
        this.confirmCheckIn = reservation.getConfirmCheckIn();
        this.confirmCheckOut = reservation.getConfirmCheckOut();
    }

    private final Integer id;
    private final String name;
    private final String contact;
    private final List<RoomDto> rooms;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Boolean confirmCheckIn;
    private final Boolean confirmCheckOut;
}
