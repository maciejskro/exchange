package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Currency;

import java.util.List;


public class CurrenciesManager extends BaseManager {


    private Datastore ds;
    private Query<Currency> query;
    private String connectionString;


    CurrenciesManager(MongoClient mc, Morphia morphia) {
        super(morphia, mc);
        this.ds = super.getDatastore("exchangeOffice");
        query = getDatastore(getConnectionString()).createQuery(Currency.class);
    }

    public void save(Currency currency) {
        if (currency != null) {
            ds.save(currency);
        }
    }

    public Datastore getDs() {
        return this.ds;
    }

    public List<Currency> findAll() {
        return query.asList();

    }

}
