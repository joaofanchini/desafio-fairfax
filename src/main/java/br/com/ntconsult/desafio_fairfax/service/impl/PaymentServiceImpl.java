package br.com.ntconsult.desafio_fairfax.service.impl;

import br.com.ntconsult.desafio_fairfax.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public UUID preAuthorizePayment(String cardNumber) {
        if (Objects.isNull(cardNumber)) {
            throw new IllegalArgumentException();
        }
        return UUID.randomUUID();
    }

    @Override
    @SneakyThrows
    public UUID doPayment(UUID preAuthorizeId) {
//        Thread.sleep(Duration.ofSeconds(5));
        return UUID.randomUUID();
    }
}
