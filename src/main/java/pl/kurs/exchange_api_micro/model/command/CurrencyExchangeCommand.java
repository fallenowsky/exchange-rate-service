package pl.kurs.exchange_api_micro.model.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyExchangeCommand {

    private String from;
    private String to;
    private double amount;
}
