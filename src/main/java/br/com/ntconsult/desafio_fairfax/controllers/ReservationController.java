package br.com.ntconsult.desafio_fairfax.controllers;

import br.com.ntconsult.desafio_fairfax.dtos.ReservationDto;
import br.com.ntconsult.desafio_fairfax.requests.ReservationRequest;
import br.com.ntconsult.desafio_fairfax.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationDto doReservation(@Valid @RequestBody ReservationRequest request) {
        return reservationService.doReservation(request);
    }

    @PatchMapping("/check-in/{reservationId}")
    public void checkIn(@PathVariable Integer reservationId) {
        reservationService.doCheckIn(reservationId);
    }

    @PatchMapping("/check-out/{reservationId}")
    public void checkOut(@PathVariable Integer reservationId) {
        reservationService.doCheckOut(reservationId);
    }
}
