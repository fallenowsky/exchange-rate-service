package pl.kurs.exchange_api_micro.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.kurs.exchange_api_micro.event.EmailEventPublisher;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.service.AuthDataService;

@Component
@Aspect
@RequiredArgsConstructor
public class EventPublisher {

    private final EmailEventPublisher publisher;
    private final AuthDataService authDataService;


    @AfterReturning(value = "@annotation(publishEmailEvent)", returning = "exchangedCurrency")
    public void afterCurrencyExchanged(PublishEmailEvent publishEmailEvent,
                                       CurrencyExchangeDto exchangedCurrency) {
        exchangedCurrency.setEmail(authDataService.extractAuthData());
        publisher.publishEmailEvent(exchangedCurrency);
    }

}
