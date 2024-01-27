package pl.kurs.exchange_api_micro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.exchange_api_micro.model.dto.CurrencyExchangeDto;
import pl.kurs.exchange_api_micro.model.command.CurrencyExchangeCommand;
import pl.kurs.exchange_api_micro.model.dto.CurrencyRateDto;
import pl.kurs.exchange_api_micro.service.ExchangeApiService;

//todo testy z kontenerami i mockmvc i klient z wiremock
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
public class ExchangeApiController {
    private final ExchangeApiService service;

    @GetMapping
    public Page<CurrencyRateDto> findAll(@PageableDefault Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/by-code")
    public CurrencyRateDto findByCode(@RequestParam String code) {
        return service.findByCode(code);
    }

    @GetMapping("/exchange")
    public CurrencyExchangeDto exchange(CurrencyExchangeCommand command) {
        return service.exchange(command);
    }

    @GetMapping("/exchange-admin")
    public CurrencyExchangeDto exchangeAdmin(CurrencyExchangeCommand command) {
        return service.exchangeAdmin(command);
    }

}
