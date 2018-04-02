package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

public class CurrenciesCourseManager extends BaseManager {

    private Datastore ds;
    private Query<CurrencyCourse> query;

    CurrenciesCourseManager(MongoClient mc, Morphia m) {
        super(mc,m);
        this.ds = super.getDatastore("exchangeOffice");
        query = getDatastore(getConnectionString()).createQuery(CurrencyCourse.class);
    }

    public Datastore getDs() {
        return ds;
    }

    public void save(CurrencyCourse cc) {
        if (cc != null ) {
            this.ds.save(cc);
        }
    }

    public List<CurrencyCourse> findAll() {
        return query.asList();
    }

    public CurrencyCourse findActualCourse(String code) {
        query.and(query.criteria("active").equal(true),
                 query.criteria("idCode").equal(code));
        return query.get();
    }
    public UpdateOperations<CurrencyCourse> createOperations() {
        return ds.createUpdateOperations(CurrencyCourse.class);
    }
    public UpdateResults update(CurrencyCourse currencyCourse, UpdateOperations<CurrencyCourse> operations) {
        return ds.update(currencyCourse, operations);
    }

    public void remove (CurrencyCourse curr) {
        ds.delete(curr);
    }
}
