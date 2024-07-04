package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import lombok.Getter;

@Getter
public class ReservationDto {
    public ReservationDto(Reservation reservation){
        this.id = reservation.getId();
    }
    private final Integer id;
}
