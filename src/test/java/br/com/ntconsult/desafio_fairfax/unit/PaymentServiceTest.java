package br.com.ntconsult.desafio_fairfax.unit;

import br.com.ntconsult.desafio_fairfax.service.PaymentService;
import br.com.ntconsult.desafio_fairfax.service.impl.PaymentServiceImpl;
import br.com.ntconsult.desafio_fairfax.unit.base.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class PaymentServiceTest extends AbstractTest {

    PaymentService paymentService = new PaymentServiceImpl();

    @Test
    void shouldPreAuthorizePayment() {
        // when
        UUID uuid = paymentService.preAuthorizePayment("123123");

        // then
        Assertions.assertThat(uuid).isNotNull();
    }

    @Test
    void shouldGetAnErrorWhenReceiveIllegalArgumentToPreAuthorizePayment() {
        // when
        IllegalArgumentException illegalArgumentException = Assertions.catchThrowableOfType(() -> paymentService.preAuthorizePayment(null), IllegalArgumentException.class);

        // then
        Assertions.assertThat(illegalArgumentException).isNotNull();
    }

    @Test
    void shouldDoPayment() {
        // given
        UUID preAuthorizedPaymentUUID = Mockito.mock(UUID.class);

        // when
        UUID paymentUUID = paymentService.doPayment(preAuthorizedPaymentUUID);

        // then
        Assertions.assertThat(paymentUUID).isNotNull();
    }
}
