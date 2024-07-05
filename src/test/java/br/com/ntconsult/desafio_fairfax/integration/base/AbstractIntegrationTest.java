package br.com.ntconsult.desafio_fairfax.integration.base;

import br.com.ntconsult.desafio_fairfax.data.repositories.HotelRepository;
import br.com.ntconsult.desafio_fairfax.data.repositories.ReservationRepository;
import br.com.ntconsult.desafio_fairfax.data.repositories.RoomRepository;
import br.com.ntconsult.desafio_fairfax.integration.config.TestcontainersConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("testing")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class AbstractIntegrationTest {

    @Autowired
    protected ReservationRepository reservationRepository;

    @Autowired
    protected RoomRepository roomRepository;

    @Autowired
    protected HotelRepository hotelRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
    }
}
