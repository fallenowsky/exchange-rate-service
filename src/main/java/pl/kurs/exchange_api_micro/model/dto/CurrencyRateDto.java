package pl.kurs.exchange_api_micro.model.dto;

import lombok.Builder;
import lombok.Data;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateDto {

    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;

    public static CurrencyRateDto mapToDto(CurrencyRate currencyRate) {
        return CurrencyRateDto.builder()
                .currency(currencyRate.getCurrency())
                .code(currencyRate.getCode())
                .bid(currencyRate.getBid())
                .ask(currencyRate.getAsk())
                .build();
    }
}
