package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kayzone.exchange.model.entity.Currency;

import java.util.List;


public class CurrenciesManagerImpl extends BaseManagerImpl<Currency> implements CurrenciesManager {


    private final Datastore ds;
    private Query<Currency> query;
    private static final Logger LOGG = LoggerFactory.getLogger(CurrenciesManagerImpl.class);

    CurrenciesManagerImpl(MongoClient mc) {
        super(mc);
        ds = super.getDatastore("exchangeOffice");
        query = super.getDatastore(getConnectionString()).createQuery(Currency.class);
    }

    public Datastore getDs() {
        return this.ds;
    }

    public void save(Currency currency) {
       super.save(currency);
    }

    public List<Currency> findAll() {
        return super.findAll();

    }
    public Currency find(String id) {
        return ds.get(Currency.class, id);
    }

    public UpdateOperations<Currency> createOperations() {
        return ds.createUpdateOperations(Currency.class);
    }
    public UpdateResults update(Currency currency, UpdateOperations<Currency> operations) {
        return ds.update(currency, operations);
    }

    public void remove (Currency curr) {
        ds.delete(curr);
    }

}
