package pl.kurs.exchange_api_micro.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateDto {

    private String currency;

    private String code;

    private BigDecimal bid;

    private BigDecimal ask;
}
