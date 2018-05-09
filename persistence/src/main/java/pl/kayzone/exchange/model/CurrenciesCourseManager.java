package pl.kayzone.exchange.model;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

public interface CurrenciesCourseManager extends BaseManager<CurrencyCourse>   {

    void save(CurrencyCourse cc);
    List<CurrencyCourse> findAll();
    CurrencyCourse findById (String id);
    CurrencyCourse findActualCourse(String code);
    UpdateOperations<CurrencyCourse> createOperations();
    UpdateResults update(Query<CurrencyCourse> currencyCourse, UpdateOperations<CurrencyCourse> operations);
    void remove (CurrencyCourse curr);

}
