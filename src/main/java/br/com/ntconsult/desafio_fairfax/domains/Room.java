package br.com.ntconsult.desafio_fairfax.domains;

import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Room {
    public Room(Hotel hotel, Integer maxGuests, Double price, RoomStatus status) {
        this.hotel = hotel;
        this.maxGuests = maxGuests;
        this.price = price;
        this.status = status;
    }

    @Id
    private Integer id;

    @ManyToOne
    @NotNull
    private Hotel hotel;

    private Integer maxGuests;

    private Double price;

    @Enumerated(value = EnumType.STRING)
    private RoomStatus status;

    @ManyToMany
    @JoinTable(name = "room_facilities",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilities;
}
