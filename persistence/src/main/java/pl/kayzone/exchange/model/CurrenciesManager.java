package pl.kayzone.exchange.model;

import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.Currency;

import java.util.List;

public interface CurrenciesManager extends BaseManager<Currency> {

    void save(Currency currency);
    List<Currency> findAll();
    UpdateOperations<Currency> createOperations();
    UpdateResults update(Currency currency, UpdateOperations<Currency> operations);
    void remove (Currency curr);
}
