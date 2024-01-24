package pl.kurs.exchange_api_micro.model.command;

import lombok.Data;

@Data
public class CurrencyExchangeCommand {
    private String from;
    private String to;
    private double amount;
}
