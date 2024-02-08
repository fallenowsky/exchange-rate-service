package pl.kurs.exchange_api_micro.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
public class RabbitProperties {

    private String queueName;
}
