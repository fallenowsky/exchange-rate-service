package pl.kurs.exchange_api_micro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

public interface ExchangeApiRepository extends MongoRepository<CurrencyRate, Integer> {
    Page<CurrencyRate> findByCode(String code, Pageable pageable);
    CurrencyRate findByCode(String code);
}
