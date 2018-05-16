package pl.kayzone.exchange.controler;

import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CoursesCurrencyServices {

    void addNewCurrentCourses(String code , LocalDateTime validFrom, LocalDateTime validTo, BigDecimal sell , BigDecimal buy);
    List<CurrencyCourse> getActiveCourses();
    CurrencyCourse getActiveCourseForCode(String code);
    List<Currency> getActiveCurrencies();
    void activateCurrency(String code);
    void deactivateCurrency(String code);
    void addCurrency(Currency currency);
    void removeCurrency(String code);
}
