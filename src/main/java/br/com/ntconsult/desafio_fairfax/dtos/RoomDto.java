package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class RoomDto {
    public RoomDto(Room room) {
        this.id = room.getId();
        this.status = room.getStatus();
        this.maxGuests = room.getMaxGuests();
        this.price = room.getPrice();
        this.facilities = Optional.ofNullable(room.getFacilities()).orElse(new ArrayList<>())
                .stream()
                .map(FacilityDto::new)
                .toList();
    }

    private final Integer id;
    private final RoomStatus status;
    private final Integer maxGuests;
    private final Double price;
    private final List<FacilityDto> facilities;
}
