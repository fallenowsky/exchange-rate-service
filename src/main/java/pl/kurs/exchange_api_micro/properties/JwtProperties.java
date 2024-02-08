package pl.kurs.exchange_api_micro.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.auth.converter")
@Getter
@Setter
public class JwtProperties {

    private String resourceId;
    private String principleAttribute;

}
