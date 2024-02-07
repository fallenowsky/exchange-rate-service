package pl.kurs.exchange_api_micro.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyExchangeDto {

    private String from;
    private String to;
    private double amount;
    private BigDecimal result;
    private String email;
}
