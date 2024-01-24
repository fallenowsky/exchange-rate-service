package pl.kurs.exchange_api_micro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.exchange_api_micro.service.ExchangeApiService;

//todo testy z kontenerami i mockmvc i klient z wiremock
@RestController
@RequiredArgsConstructor
public class ExchangeApiController {

    private final ExchangeApiService exchangeApiService;


}
