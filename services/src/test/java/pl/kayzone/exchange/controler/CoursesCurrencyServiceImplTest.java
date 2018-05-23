package pl.kayzone.exchange.controler;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.kayzone.exchange.model.*;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({CurrenciesCourseManager.class, CurrenciesManager.class})
public class CoursesCurrencyServiceImplTest {

    private MongoClient mockClient;
    private CurrenciesManager mockcm ;
    private CurrenciesCourseManager mockccm;

    private CoursesCurrencyServices ccs;
    private TestClassCreator tcc;

    @Before
    public void setUp() throws  Exception {
        this.tcc = new TestClassCreator();
        mockClient = mock(MongoClient.class);
        mockcm = mock(CurrenciesManagerImpl.class);
        mockccm = mock(CurrenciesCourseManagerImpl.class);
        whenNew(CurrenciesCourseManager.class).withNoArguments().thenReturn(mockccm);
        whenNew(CurrenciesManager.class).withNoArguments().thenReturn(mockcm);
        this.ccs = new CoursersCurrencyServiceImpl(mockClient);
        this.ccs = spy(ccs);
    }

    @Test
    public  void shouldGetCurrentCurrenciesForCode(){
        when( mockccm.findActualCourse("USD") ).thenReturn( tcc.getCurrencyCourse() );


        CurrencyCourse cc  = ccs.getActiveCourseForCode( "USD" );

        assertThat(cc).isInstanceOf( CurrencyCourse.class );
        assertThat(cc).
               isEqualToComparingOnlyGivenFields( tcc.getCurrencyCourse(),"active", "ask", "bid" );
        Mockito.verify(mockccm).findActualCourse("USD");
    }

    @Test
    public void souldGetNullCurrenciesForUnknownCode() {

        CurrencyCourse cc = ccs.getActiveCourseForCode("unknown");

        assertThat(cc).isNull();
    }
    @Test
    public void souldGetListOfActiveCurrenciesCourse() {
        when(mockccm.findAllActive()).thenReturn(Arrays.asList(tcc.getCurrencyCourse(), tcc.getCurrencyCourse()));

        List<CurrencyCourse> lcc = ccs.getActiveCourses();

        assertThat(lcc).hasOnlyElementsOfTypes(CurrencyCourse.class);
        assertThat(lcc.size()).isEqualTo(2);
        Mockito.verify(mockccm).findAllActive();
    }
}
