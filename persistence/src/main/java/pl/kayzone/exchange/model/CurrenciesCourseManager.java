package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

public class CurrenciesCourseManager extends BaseManager {

    private Datastore ds;
    private Query<CurrencyCourse> query;

    CurrenciesCourseManager(Morphia m, MongoClient mc) {
        super(m, mc);
        this.ds = super.getDatastore("exchangeOffice");
        query = getDatastore(getConnectionString()).createQuery(CurrencyCourse.class);
    }
    public void save(CurrencyCourse cc) {
        this.ds.save(cc);
    }
    public List<CurrencyCourse> findAll() {
        return query.asList();
    }

}
