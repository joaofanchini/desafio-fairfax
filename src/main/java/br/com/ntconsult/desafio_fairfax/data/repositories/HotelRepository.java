package br.com.ntconsult.desafio_fairfax.data.repositories;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer>, JpaSpecificationExecutor<Hotel> {
}
