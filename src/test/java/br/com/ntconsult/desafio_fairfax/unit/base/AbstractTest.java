package br.com.ntconsult.desafio_fairfax.unit.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public abstract class AbstractTest {
    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterEach() {
        Mockito.clearAllCaches();
    }
}
