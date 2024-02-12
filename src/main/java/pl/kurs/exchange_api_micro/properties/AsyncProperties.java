package pl.kurs.exchange_api_micro.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app.async")
@Getter
@Setter
public class AsyncProperties {

    private int threads;
}
