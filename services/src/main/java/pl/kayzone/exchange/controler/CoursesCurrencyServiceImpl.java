package pl.kayzone.exchange.controler;

import com.mongodb.MongoClient;
import pl.kayzone.exchange.model.CurrenciesCourseManager;
import pl.kayzone.exchange.model.CurrenciesCourseManagerImpl;
import pl.kayzone.exchange.model.CurrenciesManager;
import pl.kayzone.exchange.model.CurrenciesManagerImpl;
import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CoursesCurrencyServiceImpl implements CoursesCurrencyServices {

    private CurrenciesManager cm;
    private CurrenciesCourseManager ccm;

    public CoursesCurrencyServiceImpl(MongoClient mongoClient) {
        if (mongoClient != null) {
            this.cm = new CurrenciesManagerImpl(mongoClient);
            this.ccm = new CurrenciesCourseManagerImpl(mongoClient);
        } else {
            this.cm = new CurrenciesManagerImpl(new MongoClient());
            this.ccm = new CurrenciesCourseManagerImpl(new MongoClient());
        }
    }

    public CoursesCurrencyServiceImpl() {
    }

    @Override
    public void addNewCurrentCourses(String code, LocalDateTime validFrom, LocalDateTime validTo, BigDecimal sell, BigDecimal buy) {
    }

    @Override
    public List<CurrencyCourse> getActiveCourses() {
        return ccm.findAllActive();
    }

    @Override
    public CurrencyCourse getActiveCourseForCode(String code) {
        return ccm.findActualCourse(code);
    }

    @Override
    public List<Currency> getActiveCurrencies() {
        return cm.findAll();
    }

    @Override
    public void activateCurrency(String code) {

    }

    @Override
    public void deactivateCurrency(String code) {

    }

    @Override
    public void addCurrency(Currency currency) {

    }

    @Override
    public void removeCurrency(String code) {

    }
}
