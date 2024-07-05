package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class HotelDto {
    public HotelDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.facilities = Optional.ofNullable(hotel.getFacilities()).orElse(new ArrayList<>())
                .stream()
                .map(FacilityDto::new).toList();
        this.rooms = Optional.ofNullable(hotel.getRooms()).orElse(new ArrayList<>())
                .stream()
                .map(RoomDto::new)
                .toList();
    }

    private Integer id;
    private String name;
    private List<FacilityDto> facilities;
    private List<RoomDto> rooms;
}
