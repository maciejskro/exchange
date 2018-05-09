package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kayzone.exchange.model.entity.Customers;

import java.util.List;

public class CustomerManagerImpl extends BaseManagerImpl<Customers> implements CustomerManager {

    private final Datastore ds;
    private Query<Customers> query ;
    private static final Logger LOGG = LoggerFactory.getLogger(CurrenciesManagerImpl.class);

    public CustomerManagerImpl(MongoClient mc, Morphia m) {
        super(mc);
        this.ds = getDatastore("exchangeOffice");
        this.query = getDatastore(getConnectionString()).createQuery(Customers.class);
    }

    public Datastore getDs() {
        return this.ds;
    }

    public void save(Customers c) {
        super.save(c);
    }



    public List<Customers> findAll() {
        return null;
    }

    public Customers find(ObjectId id) {
        return null;
    }
}
