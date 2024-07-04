package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import lombok.Getter;

import java.util.List;

@Getter
public class HotelDto {
    public HotelDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.facilities = hotel.getFacilities().stream().map(FacilityDto::new).toList();
        this.rooms = hotel.getRooms().stream().map(RoomDto::new).toList();
    }

    private final Integer id;
    private final String name;
    private final List<FacilityDto> facilities;
    private final List<RoomDto> rooms;
}
