package br.com.ntconsult.desafio_fairfax.unit;

import br.com.ntconsult.desafio_fairfax.domains.Hotel;
import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import br.com.ntconsult.desafio_fairfax.domains.Room;
import br.com.ntconsult.desafio_fairfax.enums.RoomStatus;
import br.com.ntconsult.desafio_fairfax.service.NotificationService;
import br.com.ntconsult.desafio_fairfax.service.impl.NotificationServiceImpl;
import br.com.ntconsult.desafio_fairfax.unit.base.AbstractTest;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.scheduling.JobScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class NotificationServiceTest extends AbstractTest {

    @Mock
    private JobScheduler scheduler;
    private NotificationService notificationService;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        when(scheduler.schedule(any(LocalDateTime.class), any(JobLambda.class)))
                .thenAnswer(answer -> {
                    JobLambda job = answer.getArgument(1, JobLambda.class);
                    job.run();
                    return new JobId(UUID.randomUUID());
                });
        notificationService = new NotificationServiceImpl(scheduler);
    }

    @Test
    public void shouldNotify() {
        // given
        UUID preAuthorizePaymentUUID = UUID.randomUUID();
        Hotel hotel = new Hotel("Intercity", "São Paulo");
        Room room = new Room(hotel, 2, 200.0, RoomStatus.AVAILABLE);
        Reservation reservation = new Reservation(List.of(room), "Daniel",
                "11998989898",
                LocalDate.now(),
                LocalDate.now().plusDays(2),
                preAuthorizePaymentUUID);

        // when
        notificationService.scheduleCheckInCheckOutNotifications(reservation);

        // then
        verify(scheduler, times(2)).schedule(Mockito.any(LocalDateTime.class), Mockito.any(JobLambda.class));
    }
}
