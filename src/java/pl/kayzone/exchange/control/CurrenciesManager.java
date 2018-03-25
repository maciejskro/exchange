package pl.kayzone.exchange.control;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import pl.kayzone.exchange.control.helpers.BaseManager;
import pl.kayzone.exchange.entity.CurrencyCourse;

public class CurrenciesManager extends BaseManager {


    private Datastore ds;


    CurrenciesManager(MongoClient mc, Morphia morphia) {
        super(morphia, mc);
        this.ds = super.getDatastore("exchangeOffice");

    }

    public void save(CurrencyCourse currencyCourse) {
        ds.save(currencyCourse);
    }

    public Datastore getDs() {
        return this.ds;
    }

}
