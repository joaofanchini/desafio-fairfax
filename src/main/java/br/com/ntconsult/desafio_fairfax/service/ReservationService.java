package br.com.ntconsult.desafio_fairfax.service;

import br.com.ntconsult.desafio_fairfax.dtos.ReservationDto;
import br.com.ntconsult.desafio_fairfax.requests.ReservationRequest;

public interface ReservationService {
    ReservationDto doReservation(ReservationRequest request);

    void doCheckIn(Integer reservationId);

    void doCheckOut(Integer reservationId);
}
