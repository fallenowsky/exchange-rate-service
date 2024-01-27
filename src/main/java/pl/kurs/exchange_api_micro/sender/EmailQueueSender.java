package pl.kurs.exchange_api_micro.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;

@Service
@RequiredArgsConstructor
public class EmailQueueSender {

    private final RabbitTemplate rabbitTemplate;


    public void sendCurrencyExchange(CurrencyExchangeDto exchange) {
        rabbitTemplate.convertAndSend(exchange);
    }
}

