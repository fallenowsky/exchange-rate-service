package pl.kurs.exchange_api_micro.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyExchangeDto {

    private String from;
    private String to;
    private double amount;
    private BigDecimal result;
}
