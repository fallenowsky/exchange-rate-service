package pl.kurs.exchange_api_micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.exchange_api_micro.model.CurrencyRate;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.model.command.CurrencyExchangeCommand;
import pl.kurs.exchange_api_micro.model.dto.CurrencyRateDto;
import pl.kurs.exchange_api_micro.repository.ExchangeApiRepository;
import pl.kurs.exchange_api_micro.sender.EmailQueueSender;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ExchangeApiService {

    private final ExchangeApiRepository repository;
    private final EmailQueueSender sender;


    public Page<CurrencyRateDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(CurrencyRateDto::mapToDto);
    }

    public CurrencyRateDto findByCode(String code) {
        return CurrencyRateDto.mapToDto(repository.findByCode(code));
    }

    public CurrencyExchangeDto exchange(CurrencyExchangeCommand command) {
        CurrencyExchangeDto exchange = CurrencyExchangeDto.builder()
                .from(command.getFrom())
                .to(command.getTo())
                .amount(command.getAmount())
                .result(calculateResult(command.getFrom(), command.getAmount()))
                .build();
        sender.sendCurrencyExchange(exchange);
        return exchange;
    }

    private BigDecimal calculateResult(String from, double amount) {
        return repository.findByCode(from).getBid()
                .multiply(BigDecimal.valueOf(amount));
    }

    public CurrencyExchangeDto exchangeAdmin(CurrencyExchangeCommand command) {
        return null;
    }
}
