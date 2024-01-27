package pl.kurs.exchange_api_micro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

public interface ExchangeApiRepository extends MongoRepository<CurrencyRate, Integer> {

    CurrencyRate findByCode(String code);

}
