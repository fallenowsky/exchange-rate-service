package pl.kurs.exchange_api_micro.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyExchange {
    private String from;
    private String to;
    private double amount;
    private BigDecimal result;
}
