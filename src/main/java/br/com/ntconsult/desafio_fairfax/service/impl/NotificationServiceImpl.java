package br.com.ntconsult.desafio_fairfax.service.impl;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;
import br.com.ntconsult.desafio_fairfax.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JobScheduler scheduler;

    @Override
    public void scheduleCheckInCheckOutNotifications(Reservation reservation) {
        scheduler.schedule(reservation.getCheckIn().atStartOfDay(), () -> System.out.printf("Olá %s, faça a sua confirmação de Check In", reservation.getName()));
        scheduler.schedule(reservation.getCheckOut().atStartOfDay(), () -> System.out.printf("Olá %s, faça a sua confirmação de Check Out", reservation.getName()));
    }
}
