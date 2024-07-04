package br.com.ntconsult.desafio_fairfax.data.repositories;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
