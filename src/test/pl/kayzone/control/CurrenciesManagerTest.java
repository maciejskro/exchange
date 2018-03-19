package pl.kayzone.control;

import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;


public class CurrenciesManagerTest {

    private CurrenciesManager currenciesManager;

    @Before
    public void setUp() {
        this.currenciesManager = new CurrenciesManager();
    }

    private Object[][] showParameters() {
        return new Object[][] {
                {},
                {},
                {}
        };
    }

    @Test
    @Parameters(method = "showParameters")
    public void shouldShowListOfAvailableCurrencies() {



    }

}

