package pl.kayzone.exchange.control;

import com.mongodb.MongoClient;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import pl.kayzone.exchange.entity.Currency;
import pl.kayzone.exchange.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class CurrenciesManagerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock private Morphia mockMorphia;
    @Mock private MongoClient mockMongoClient;
    @Mock private Datastore mockDs;
    private CurrenciesManager currenciesManager;

    @Before()
    public void setUp() {
        this.currenciesManager = new CurrenciesManager(mockMongoClient,mockMorphia);
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
    public void shouldShowListOfAvailableCurrencies(String code , Double rate , Double bid, Double ask) {
        // Currency currency = new Currency (code , rate);
        CurrencyCourse currencyCourse = new CurrencyCourse(code, code,
                    new BigDecimal(bid, MathContext.DECIMAL64),
                    new BigDecimal(ask,MathContext.DECIMAL64));

         currenciesManager.save(currencyCourse);

        //assertThat(currenciesManager.getDs()).returns();
        Mockito.verify(currenciesManager).save(currencyCourse);

    }

    }

