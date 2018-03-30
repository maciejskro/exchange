package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.Currency;

import java.util.List;


public class CurrenciesManager extends BaseManager {


    private Datastore ds;
    private Query<Currency> query;


    CurrenciesManager(MongoClient mc) {
        super(mc);
        this.ds = super.getDatastore("exchangeOffice");
        query = getDatastore(getConnectionString()).createQuery(Currency.class);
    }

    public Datastore getDs() {
        return this.ds;
    }

    public void save(Currency currency) {
        if (currency != null) {
            ds.save(currency);
        }
    }

    public List<Currency> findAll() {
        return query.asList();

    }
    public Currency find(ObjectId id) {
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
