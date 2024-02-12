package pl.kurs.exchange_api_micro.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;

@Component
@RequiredArgsConstructor
public class EmailEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;


    public void publishEmailEvent(CurrencyExchangeDto command) {
        applicationEventPublisher.publishEvent(new EmailEvent<>(command));
    }

}
