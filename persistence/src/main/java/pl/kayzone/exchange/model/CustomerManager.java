package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kayzone.exchange.model.entity.Customers;

public class CustomerManager extends BaseManager<Customers> {

    private final Datastore ds;
    private Query<Customers> query ;
    private static final Logger LOGG = LoggerFactory.getLogger(CurrenciesManager.class);

    public CustomerManager(MongoClient mc, Morphia m) {
        super(mc, m);
        this.ds = getDatastore("exchangeOffice");
        this.query = getDatastore(getConnectionString()).createQuery(Customers.class);
    }

    public Datastore getDs() {
        return this.ds;
    }
}
