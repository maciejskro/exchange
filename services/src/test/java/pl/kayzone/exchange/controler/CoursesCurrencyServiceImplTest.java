package pl.kayzone.exchange.controler;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.CurrenciesCourseManager;
import pl.kayzone.exchange.model.CurrenciesManager;
import pl.kayzone.exchange.model.TestClassCreator;
import pl.kayzone.exchange.model.entity.CurrencyCourse;


@RunWith(MockitoJUnitRunner.class)
public class CoursesCurrencyServiceImplTest {

    @Mock
    CurrenciesCourseManager currenciesCourseManager;

    @Mock
    CurrenciesManager currenciesManager;

    @Mock
    Datastore ds;
    @Mock
    Query query;
    private TestClassCreator tcc;


    @Before
    public void setUp() {
        this.tcc = new TestClassCreator();
    }

    @Test
    public  void getCurrentCurrenciesCode(){
        Mockito.when(currenciesCourseManager.findActualCourse("USD")).thenReturn(tcc.getCurrencyCourse());

        CurrencyCourse cc  = currenciesCourseManager.findActualCourse("USD");

        Assertions.assertThat(cc).isInstanceOf(CurrencyCourse.class);
        Assertions.assertThat(cc).
                isEqualToComparingOnlyGivenFields(tcc.getCurrencyCourse(),"active", "ask", "bid","idCode");
    }
}
