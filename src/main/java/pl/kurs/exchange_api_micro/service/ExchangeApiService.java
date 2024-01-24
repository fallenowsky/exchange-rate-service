package pl.kurs.exchange_api_micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.exchange_api_micro.model.CurrencyRate;
import pl.kurs.exchange_api_micro.model.CurrencyExchange;
import pl.kurs.exchange_api_micro.model.command.CurrencyExchangeCommand;
import pl.kurs.exchange_api_micro.repository.ExchangeApiRepository;
import pl.kurs.exchange_api_micro.sender.EmailQueueSender;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeApiService {
    private final ExchangeApiRepository repository;
    private final EmailQueueSender sender;

    public Page<CurrencyRate> findAll(String code, Pageable pageable) {
        if (code != null) {
            return repository.findByCode(code, pageable);
        }
        return repository.findAll(pageable);
    }

    public CurrencyRate findByCode(String code) {
        return repository.findByCode(code);
    }

    public CurrencyExchange exchange(CurrencyExchangeCommand command) {
        CurrencyExchange exchange = CurrencyExchange.builder()
                .from(command.getFrom())
                .to(command.getTo())
                .amount(command.getAmount())
                .result(calculateResult(command.getTo(), command.getAmount()))
                .build();
        sender.sendCurrencyExchange(exchange);
        return exchange;
    }

    private BigDecimal calculateResult(String to, double amount) {
        CurrencyRate toRate = findByCode(to);
        return toRate.getBid().multiply(BigDecimal.valueOf(amount));

    }

    public CurrencyExchange exchangeAdmin(CurrencyExchangeCommand command) {
        return null;
    }
}
