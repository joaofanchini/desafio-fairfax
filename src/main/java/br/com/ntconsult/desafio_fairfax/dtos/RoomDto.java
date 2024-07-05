package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
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

    private Integer id;
    private RoomStatus status;
    private Integer maxGuests;
    private Double price;
    private List<FacilityDto> facilities;
}
