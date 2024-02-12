package pl.kurs.exchange_api_micro.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kurs.exchange_api_micro.common.TestContainer;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExchangeApiRepositoryTest implements TestContainer {

    @SpyBean
    private ExchangeApiRepository repository;

    private CurrencyRate dollar;
    private CurrencyRate frank;


    @BeforeEach
    public void setUp() {
        dollar = CurrencyRate.builder()
                .code("USD")
                .currency("US Dollar")
                .bid(BigDecimal.valueOf(3.99))
                .ask(BigDecimal.valueOf(4.15))
                .build();
        frank = CurrencyRate.builder()
                .code("CHF")
                .currency("Frank")
                .bid(BigDecimal.valueOf(4.49))
                .ask(BigDecimal.valueOf(4.61))
                .build();
    }


    @Test
    public void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    public void testFindByCode_HappyPath_ResultsInCurrencyBeingReturned() {
        CurrencyRate usd = repository.findByCode("USD").orElseGet(CurrencyRate::new);
        CurrencyRate chf = repository.findByCode("CHF").orElseGet(CurrencyRate::new);

        assertThat(usd).usingRecursiveComparison().isEqualTo(dollar);
        assertThat(chf).usingRecursiveComparison().isEqualTo(frank);
        verify(repository, times(2)).findByCode(anyString());
    }

    @Test
    public void testFindByCode_CurrencyNotExists_ResultsInNullBeingReturned() {
        CurrencyRate klp = repository.findByCode("KLP").orElse(null);

        assertNull(klp);
        verify(repository, times(1)).findByCode("KLP");
    }
}