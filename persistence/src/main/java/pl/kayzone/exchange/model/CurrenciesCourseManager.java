package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

public class CurrenciesCourseManager extends BaseManager {

    private Datastore ds;
    private Query<CurrencyCourse> query;

    CurrenciesCourseManager(MongoClient mc) {
        super(mc);
        this.ds = super.getDatastore("exchangeOffice");
        query = getDatastore(getConnectionString()).createQuery(CurrencyCourse.class);
    }
    public void save(CurrencyCourse cc) {
        this.ds.save(cc);
    }

    public List<CurrencyCourse> findAll() {
        return query.asList();
    }

    public CurrencyCourse findActualCourse(String code) {
        query.and(query.criteria("active").equal(true),
                 query.criteria("idCode").equal(code));
        return query.get();
    }
}
