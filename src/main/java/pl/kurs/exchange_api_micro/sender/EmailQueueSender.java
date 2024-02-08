package pl.kurs.exchange_api_micro.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.properties.RabbitProperties;


@Service
@RequiredArgsConstructor
public class EmailQueueSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;


    public void sendCurrencyExchange(CurrencyExchangeDto exchange) {
        rabbitTemplate.convertAndSend(rabbitProperties.getQueueName(), exchange);
    }
}

