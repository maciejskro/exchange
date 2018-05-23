package pl.kayzone.exchange.controler;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;
import pl.kayzone.exchange.model.CurrenciesCourseManagerImpl;
import pl.kayzone.exchange.model.CurrenciesManagerImpl;
import pl.kayzone.exchange.model.TestClassCreator;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;



@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest({CoursesCurrencyServiceImpl.class})
public class CoursesCurrencyServiceImplTest {

    @Mock
    private MongoClient mockClient;
    @Mock
    private CurrenciesManagerImpl cm ;
    @Mock
    private CurrenciesCourseManagerImpl ccm;

    private CoursesCurrencyServiceImpl ccs;

    private TestClassCreator tcc;

    @Before
    public void setUp() throws  Exception {
        this.tcc = new TestClassCreator();

        MockitoAnnotations.initMocks(this);

        //whenNew(CurrenciesCourseManager.class).withNoArguments();
        //whenNew(CurrenciesManager.class).withNoArguments();
        this.ccs = new CoursesCurrencyServiceImpl(mockClient);
        Whitebox.setInternalState(ccs,"ccm" ,ccm);
        Whitebox.setInternalState(ccs , "cm",cm);
    }

    @Test
    public  void shouldGetCurrentCurrenciesForCode() throws  Exception{
        Mockito.when( ccm.findActualCourse("USD") )
               .thenReturn( tcc.getCurrencyCourse() );
        //PowerMockito.doReturn(tcc.getCurrencyCourse())
         //           .when(ccs, "getActiveCourseForCode" , "USD");

        CurrencyCourse cc  = ccs.getActiveCourseForCode( "USD" );

        assertThat(cc).isInstanceOf( CurrencyCourse.class );
        assertThat(cc).
               isEqualToComparingOnlyGivenFields( tcc.getCurrencyCourse(),"active", "ask", "bid" );
        verify(ccm).findActualCourse("USD");
    }

    @Test
    public void souldGetNullCurrenciesForUnknownCode() {

        CurrencyCourse cc = ccs.getActiveCourseForCode("unknown");

        assertThat(cc).isNull();
    }
    @Test
    public void souldGetListOfActiveCurrenciesCourse() {
     //   when(mockccm.findAllActive()).thenReturn(Arrays.asList(tcc.getCurrencyCourse(), tcc.getCurrencyCourse()));

        List<CurrencyCourse> lcc = ccs.getActiveCourses();

        assertThat(lcc).hasOnlyElementsOfTypes(CurrencyCourse.class);
        assertThat(lcc.size()).isEqualTo(2);
        verify(ccm).findAllActive();
    }
}
