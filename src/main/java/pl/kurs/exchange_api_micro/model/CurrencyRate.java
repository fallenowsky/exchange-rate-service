package pl.kurs.exchange_api_micro.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rates")
public class CurrencyRate {

    @Id
    private String code;

    private String currency;
    private BigDecimal bid;
    private BigDecimal ask;
}
