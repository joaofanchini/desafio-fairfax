package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
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

    private Integer id;
    private String name;
    private String contact;
    private List<RoomDto> rooms;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Boolean confirmCheckIn;
    private Boolean confirmCheckOut;
}
