package pl.kayzone.exchange.control;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import pl.kayzone.exchange.control.helpers.BaseManager;
import pl.kayzone.exchange.entity.CurrencyCourse;

public class CurrenciesManager extends BaseManager {


    private Datastore ds;

    public CurrenciesManager() {
        super.getMorphia().mapPackage("pl.kayzone.exchange.entity");
        this.ds = super.getMorphia().createDatastore(new MongoClient(), "exchangeOffice");
        ds.ensureIndexes();
    }

    public void save(CurrencyCourse currencyCourse) {
        ds.save(currencyCourse);
    }

    public Datastore getDs() {
        return this.ds;
    }

}
