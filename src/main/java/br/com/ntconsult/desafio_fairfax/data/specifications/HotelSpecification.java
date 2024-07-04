package br.com.ntconsult.desafio_fairfax.data.specifications;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.domains.Hotel_;
import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.domains.Room_;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.ListJoin;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HotelSpecification {
    public static Specification<Hotel> advancedFilter(@Nullable LocalDate dateCheckIn,
                                                      @Nullable LocalDate dateCheckOut,
                                                      @Nullable Integer numberOfRooms,
                                                      @Nullable Integer numberOfGuests) {
        return (root, query, criteriaBuilder) -> {
            ListJoin<Hotel, Room> joinRooms = root.join(Hotel_.rooms);

            return criteriaBuilder.equal(joinRooms.get(Room_.STATUS), RoomStatus.AVAILABLE);
        };
    }
}
