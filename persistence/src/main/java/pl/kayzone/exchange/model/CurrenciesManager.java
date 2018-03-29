package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;


public class CurrenciesManager extends BaseManager {


    private Datastore ds;
    private String connectionString;


    CurrenciesManager(MongoClient mc, Morphia morphia) {
        super(morphia, mc);
        this.ds = super.getDatastore("exchangeOffice");

    }

    public void save(CurrencyCourse currencyCourse) {
        if (currencyCourse != null) {
            ds.save(currencyCourse);
        }
    }

    public Datastore getDs() {
        return this.ds;
    }

    public List<CurrencyCourse> find() {
        return getDatastore(getConnectionString()).createQuery(CurrencyCourse.class).asList();

    }
}
