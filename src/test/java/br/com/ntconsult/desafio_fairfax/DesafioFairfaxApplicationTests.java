package br.com.ntconsult.desafio_fairfax;

import br.com.ntconsult.desafio_fairfax.data.repositories.HotelRepository;
import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

//@Import(TestcontainersConfiguration.class)
@Transactional
@SpringBootTest
@Testcontainers
class DesafioFairfaxApplicationTests {

    @Autowired
    private HotelRepository hotelRepository;


    @Test
    void contextLoads() {
        List<Hotel> hotels = hotelRepository.findAll();
        Assertions.assertTrue(true);
    }

}
