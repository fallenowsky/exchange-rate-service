package pl.kurs.exchange_api_micro.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.exchange_api_micro.aop.PublishEmailEvent;
import pl.kurs.exchange_api_micro.event.EmailEventPublisher;
import pl.kurs.exchange_api_micro.model.command.CurrencyExchangeCommand;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.model.dto.CurrencyRateDto;
import pl.kurs.exchange_api_micro.repository.ExchangeApiRepository;
import pl.kurs.exchange_api_micro.sender.EmailQueueSender;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class ExchangeApiService {

    private final ExchangeApiRepository repository;


    @Transactional(readOnly = true)
    public Page<CurrencyRateDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(CurrencyRateDto::mapToDto);
    }

    public CurrencyRateDto findByCode(String code) {
        return CurrencyRateDto.mapToDto(repository.findByCode(code).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Currency with code={0} not found", code))
        ));
    }

    @PublishEmailEvent
    public CurrencyExchangeDto exchange(CurrencyExchangeCommand command) {
        return buildExchangeResult(command);
    }

    private BigDecimal calculateResult(String from, double amount) {
        return repository.findByCode(from)
                .orElseThrow(() -> new EntityNotFoundException("Currency with code " + from + " not found"))
                .getBid()
                .multiply(BigDecimal.valueOf(amount));
    }

    public CurrencyExchangeDto exchangeAdmin(CurrencyExchangeCommand command) {
        return buildExchangeResult(command);
    }

    public CurrencyExchangeDto buildExchangeResult(CurrencyExchangeCommand command) {
        return CurrencyExchangeDto.builder()
                .from(command.getFrom())
                .to(command.getTo())
                .amount(command.getAmount())
                .result(calculateResult(command.getFrom(), command.getAmount()))
                .build();
    }
}
