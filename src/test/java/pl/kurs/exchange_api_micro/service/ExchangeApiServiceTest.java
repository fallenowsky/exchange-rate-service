package pl.kurs.exchange_api_micro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import pl.kurs.exchange_api_micro.model.CurrencyRate;
import pl.kurs.exchange_api_micro.model.command.CurrencyExchangeCommand;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.model.dto.CurrencyRateDto;
import pl.kurs.exchange_api_micro.repository.ExchangeApiRepository;
import pl.kurs.exchange_api_micro.sender.EmailQueueSender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeApiServiceTest {

    @Mock
    private ExchangeApiRepository exchangeApiRepository;

    @Mock
    private EmailQueueSender emailQueueSender;

    @InjectMocks
    private ExchangeApiService service;

    private CurrencyRate frank;
    private CurrencyRate dollar;
    private List<CurrencyRate> currencies = new ArrayList<>();


    @BeforeEach
    public void init() {
         dollar = CurrencyRate.builder()
                .currency("DOLLAR")
                .code("USD")
                .bid(BigDecimal.valueOf(3.99))
                .ask(BigDecimal.valueOf(4.11))
                .build();
        frank = CurrencyRate.builder()
                .currency("FRANK")
                .code("CHF")
                .bid(BigDecimal.valueOf(4.55))
                .ask(BigDecimal.valueOf(4.65))
                .build();
        currencies.addAll(Arrays.asList(dollar, frank));
    }

    @Test
    public void testFindAll_HappyPath_ResultsInPageBeingReturned() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "ask");
        Page<CurrencyRate> page = new PageImpl<>(currencies, pageable, currencies.size());
        when(exchangeApiRepository.findAll(pageable)).thenReturn(page);

        Page<CurrencyRateDto> rateDtos = service.findAll(pageable);

        List<CurrencyRateDto> content = rateDtos.getContent();
        assertEquals(2, content.size());
        assertEquals(2, rateDtos.getTotalElements());
        assertEquals(1, rateDtos.getTotalPages());
        assertEquals(2, rateDtos.getPageable().getPageSize());
        assertTrue(rateDtos.getPageable().isPaged());
        verify(exchangeApiRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(exchangeApiRepository);
    }

    @Test
    public void testFindByCode_HappyPath_ResultsInCurrencyDto() {
        CurrencyRateDto frankDto = CurrencyRateDto.mapToDto(frank);
        when(exchangeApiRepository.findByCode("CHF")).thenReturn(frank);

        CurrencyRateDto rateDto = service.findByCode("CHF");

        assertEquals(frankDto, rateDto);
        verify(exchangeApiRepository, times(1)).findByCode("CHF");
        verifyNoMoreInteractions(exchangeApiRepository);
    }

    @Test
    public void testExchange_HappyPath_ResultsInCurrencyExchangeTo() {
        BigDecimal expectedResult = BigDecimal.valueOf(399);
        CurrencyExchangeCommand command = CurrencyExchangeCommand.builder()
                .from("USD")
                .to("PLN")
                .amount(100)
                .build();
        when(exchangeApiRepository.findByCode("USD")).thenReturn(dollar);

        CurrencyExchangeDto exchanged = service.exchange(command);

        assertEquals(expectedResult, exchanged.getResult().stripTrailingZeros());
        verify(emailQueueSender, times(1)).sendCurrencyExchange(any(CurrencyExchangeDto.class));
        verify(exchangeApiRepository, times(1)).findByCode("USD");
        verifyNoMoreInteractions(emailQueueSender, exchangeApiRepository);
    }

}