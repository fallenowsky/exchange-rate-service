package pl.kurs.exchange_api_micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kurs.exchange_api_micro.model.CurrencyRate;

import java.util.Optional;

public interface ExchangeApiRepository extends JpaRepository<CurrencyRate, Integer> {

    Optional<CurrencyRate> findByCode(String code);

}
