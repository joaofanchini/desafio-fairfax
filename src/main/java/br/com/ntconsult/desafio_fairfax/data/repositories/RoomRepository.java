package br.com.ntconsult.desafio_fairfax.data.repositories;

import br.com.ntconsult.desafio_fairfax.domains.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
