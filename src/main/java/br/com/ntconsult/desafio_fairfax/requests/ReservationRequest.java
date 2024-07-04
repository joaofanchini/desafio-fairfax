package br.com.ntconsult.desafio_fairfax.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReservationRequest {
    @NotEmpty
    private List<Integer> roomsId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String contact;
    @NotNull
    private LocalDate checkIn;
    @NotNull
    private LocalDate checkOut;
    @NotEmpty
    private String cardNumber;
}
