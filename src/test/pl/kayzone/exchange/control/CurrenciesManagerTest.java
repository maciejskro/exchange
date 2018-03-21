package pl.kayzone.exchange.control;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kayzone.exchange.entity.Currency;
import pl.kayzone.exchange.entity.CurrencyCourse;

import java.math.BigDecimal;

@RunWith(JUnitParamsRunner.class)
public class CurrenciesManagerTest {

    private CurrenciesManager currenciesManager;

    @Before
    public void setUp() {
        this.currenciesManager = new CurrenciesManager();
    }

    private Object[][] showParameters() {
        return new Object[][] {
                {"USD" , 100.0 , 3.34 , 3.45},
                {"CHF" , 1.0 , 2.23 , 3.22},
                {"GBP", 1.0, 2.33, 2.33}
        };
    }

    @Test
    @Parameters(method = "showParameters")
    public void shouldShowListOfAvailableCurrencies(String code , Double rate , BigDecimal bid, BigDecimal ask) {
            Currency currency = new Currency (code , rate);
            CurrencyCourse currencyCourse = new CurrencyCourse(code, code, bid, ask);




    }

}

