package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import lombok.Getter;

@Getter
public class RoomDto {
    public RoomDto(Room room) {
        this.id = room.getId();
        this.status = room.getStatus();
    }

    private final Integer id;
    private final RoomStatus status;
}
