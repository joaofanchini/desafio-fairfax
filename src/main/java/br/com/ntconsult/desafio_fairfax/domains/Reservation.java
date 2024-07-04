package br.com.ntconsult.desafio_fairfax.domains;

import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    public Reservation(List<Room> rooms,
                       String name,
                       String contact,
                       LocalDate checkIn,
                       LocalDate checkOut,
                       UUID preAuthorizePaymentUUID) {
        this.rooms = rooms;
        this.name = name;
        this.contact = contact;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.preAuthorizePaymentUUID = preAuthorizePaymentUUID;
    }

    @Id
    private Integer id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "reservations_rooms",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    @NotEmpty
    private List<Room> rooms;

    @NotNull
    private String name;

    @NotNull
    private String contact;

    private UUID preAuthorizePaymentUUID;

    private UUID paymentUUID;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private Boolean confirmCheckIn = false;

    @NotNull
    private LocalDate checkOut;

    @NotNull
    private Boolean confirmCheckOut = false;

    public void confirmCheckIn() {
        this.confirmCheckIn = true;
        LocalDate now = LocalDate.now();
        if (!this.checkIn.equals(now)) {
            this.checkIn = now;
        }
        this.rooms.forEach(room -> room.setStatus(RoomStatus.IN_USE));
    }

    public void confirmCheckOut() {
        this.confirmCheckOut = true;
        LocalDate now = LocalDate.now();
        if (!this.checkOut.equals(now)) {
            this.checkOut = now;
        }
        this.rooms.forEach(room -> room.setStatus(RoomStatus.AVAILABLE));
    }
}
