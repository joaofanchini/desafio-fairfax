package br.com.ntconsult.desafio_fairfax.service;

import br.com.ntconsult.desafio_fairfax.domains.Reservation;

public interface NotificationService {
    void scheduleCheckInCheckOutNotifications(Reservation reservation);
}
