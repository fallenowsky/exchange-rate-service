package pl.kurs.exchange_api_micro.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rates")
@TypeAlias("currencyRate")
public class CurrencyRate {

    @Id
    private String code;

    private String currency;
    private BigDecimal bid;
    private BigDecimal ask;
}
