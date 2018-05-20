package pl.kayzone.exchange.controler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.CurrenciesCourseManager;
import pl.kayzone.exchange.model.CurrenciesManager;
import pl.kayzone.exchange.model.TestClassCreator;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;


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
    private CoursesCurrencyServices ccs;

    @Before
    public void setUp() {
        this.tcc = new TestClassCreator();
        this.ccs  = new CoursersCurrencyServiceImpl();
    }

    @Test
    public  void getCurrentCurrenciesCode(){
        when( currenciesCourseManager.findActualCourse("USD") ).thenReturn( tcc.getCurrencyCourse() );

        CurrencyCourse cc  = ccs.getActiveCourseForCode( "USD" );

        assertThat(cc).isInstanceOf( CurrencyCourse.class );
        assertThat(cc).
                isEqualToComparingOnlyGivenFields( tcc.getCurrencyCourse(),"active", "ask", "bid");
    }
}
