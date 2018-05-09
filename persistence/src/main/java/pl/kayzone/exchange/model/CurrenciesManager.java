package pl.kayzone.exchange.model;

import pl.kayzone.exchange.model.entity.Currency;

import java.util.List;

public interface CurrenciesManager extends BaseManager<Currency> {

    void save(Currency currency);
    List<Currency> findAll();
}
