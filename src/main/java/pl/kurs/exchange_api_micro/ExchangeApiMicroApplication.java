package pl.kurs.exchange_api_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.kurs.exchange_api_micro.properties.JwtProperties;
import pl.kurs.exchange_api_micro.properties.RabbitProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtProperties.class, RabbitProperties.class})
public class ExchangeApiMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApiMicroApplication.class, args);
    }

}
