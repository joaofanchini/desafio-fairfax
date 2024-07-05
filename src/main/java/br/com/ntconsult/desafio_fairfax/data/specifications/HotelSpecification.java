package br.com.ntconsult.desafio_fairfax.data.specifications;

import br.com.ntconsult.desafio_fairfax.domains.*;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Objects;

public class HotelSpecification {
    public static Specification<Hotel> advancedFilter(@Nullable LocalDate dateCheckIn,
                                                      @Nullable LocalDate dateCheckOut,
                                                      @Nullable Integer numberOfRooms,
                                                      @Nullable Integer numberOfGuests) {
        return (root, query, criteriaBuilder) -> {
            ListJoin<Hotel, Room> joinRooms = root.join(Hotel_.rooms);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(joinRooms.get(Room_.STATUS), RoomStatus.AVAILABLE),
                    Objects.nonNull(dateCheckIn) && Objects.nonNull(dateCheckOut) ?
                            byDatesCheckInCheckOut(dateCheckIn, dateCheckOut, joinRooms, query, criteriaBuilder) : criteriaBuilder.conjunction(),
                    Objects.nonNull(numberOfRooms) ? numberOfRooms(numberOfRooms, root, query, criteriaBuilder) : criteriaBuilder.conjunction(),
                    Objects.nonNull(numberOfGuests) ? numberOfGuests(numberOfGuests, joinRooms, criteriaBuilder) : criteriaBuilder.conjunction()
            );
        };
    }

    public static Predicate byDatesCheckInCheckOut(LocalDate dateCheckIn, LocalDate dateCheckOut,
                                                   ListJoin<Hotel, Room> hotelRooms, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<Integer> subQuery = query.subquery(Integer.class);
        Root<Reservation> reservationRoot = subQuery.from(Reservation.class);
        ListJoin<Reservation, Room> reservationRooms = reservationRoot.join(Reservation_.rooms);

        subQuery.select(criteriaBuilder.literal(1));
        subQuery.where(
                criteriaBuilder.and(
                        reservationRooms.get(Room_.id).in(hotelRooms.get(Room_.id)),
                        criteriaBuilder.between(reservationRoot.get(Reservation_.CHECK_IN), dateCheckIn, dateCheckOut),
                        criteriaBuilder.between(reservationRoot.get(Reservation_.CHECK_OUT), dateCheckIn, dateCheckOut)
                )
        );

        return criteriaBuilder.exists(subQuery).not();
    }

    public static Predicate numberOfRooms(Integer numberOfRooms,
                                          Root<Hotel> hotelRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<Integer> subQuery = query.subquery(Integer.class);
        Root<Hotel> subQueryHotel = subQuery.from(Hotel.class);
        ListJoin<Hotel, Room> subQueryHotelRooms = subQueryHotel.join(Hotel_.rooms);

        subQuery.select(criteriaBuilder.literal(1));
        subQuery.groupBy(subQueryHotel.get(Hotel_.id));

        subQuery.having(
                criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.count(subQueryHotelRooms.get(Room_.id)).as(Integer.class), numberOfRooms)
        );

        subQuery.where(
                criteriaBuilder.equal(subQueryHotel.get(Hotel_.id), hotelRoot.get(Hotel_.id))
        );

        return criteriaBuilder.exists(subQuery);
    }

    public static Predicate numberOfGuests(Integer numberOfGuests,
                                           ListJoin<Hotel, Room> hotelRooms, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThanOrEqualTo(hotelRooms.get(Room_.maxGuests), numberOfGuests);
    }
}
