package pl.kurs.exchange_api_micro.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.sender.EmailQueueSender;

@Component
@RequiredArgsConstructor
public class EmailEventHandler {

    private final EmailQueueSender sender;


    @EventListener
    @Async("asyncExecutor")
    public void handleEmailEvent(EmailEvent<CurrencyExchangeDto> emailEvent) {
        sender.sendCurrencyExchange(emailEvent.getData());
    }
}
