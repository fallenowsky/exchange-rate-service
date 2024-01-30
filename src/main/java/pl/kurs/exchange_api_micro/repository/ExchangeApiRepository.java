package pl.kurs.exchange_api_micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

public interface ExchangeApiRepository extends JpaRepository<CurrencyRate, Integer> {

    CurrencyRate findByCode(String code);

}
