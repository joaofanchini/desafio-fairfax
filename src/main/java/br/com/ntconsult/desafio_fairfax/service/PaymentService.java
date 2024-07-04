package br.com.ntconsult.desafio_fairfax.service;

import java.util.UUID;

public interface PaymentService {
    UUID preAuthorizePayment(String cardNumber);
    UUID doPayment(UUID preAuthorizeId);
}
