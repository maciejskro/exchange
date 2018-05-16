package pl.kayzone.exchange.controler;

import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CoursersCurrencyServiceImpl implements CoursesCurrencyServices {
    @Override
    public void addNewCurrentCourses(String code, LocalDateTime validFrom, LocalDateTime validTo, BigDecimal sell, BigDecimal buy) {

    }

    @Override
    public List<CurrencyCourse> getActiveCourses() {
        return null;
    }

    @Override
    public CurrencyCourse getActiveCourseForCode(String code) {
        return null;
    }

    @Override
    public List<Currency> getActiveCurrencies() {
        return null;
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
