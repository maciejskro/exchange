package pl.kayzone.exchange.model.entity;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kayzone.exchange.model.TestClassCreator;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class CurrencyCourseTest {
    //Field id of type ObjectId - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field date of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field validTo of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    BigDecimal bid;
    @Mock
    BigDecimal ask;
    @InjectMocks
    CurrencyCourse currencyCourse;
    TestClassCreator tcc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tcc = new TestClassCreator();
    }

    @Test
    public void testToString() throws Exception {
        String result = (new TestClassCreator()).getCurrency().toString();
        Assert.assertEquals("Currency{idCode='USD', name='dolar amerykański'," +
                " urlNbp='http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/', tablesType='A', rates=1.0}", result);
    }

    @Test
    public void testEquals() throws Exception {
        boolean result = currencyCourse.equals(null);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testHashCode() throws Exception {
        int result = currencyCourse.hashCode();
        Assert.assertNotEquals(0, result);
    }

    @Test
    public  void testLikeNull() {
        CurrencyCourse cc = new CurrencyCourse();
        cc.setId(new ObjectId());
        boolean result = cc.isLikeNull();

        assertThat(cc).isNotNull();
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testLikeNullNotNull() {
        CurrencyCourse cc = tcc.getCurrencyCourse();

        boolean result = cc.isLikeNull();

        assertThat(cc).isNotNull();
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testStrictNBPlink() {
        CurrencyCourse cc = tcc.getCurrencyCourse();

        String result = cc.getStrictNBPuriAddress();

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo("http://api.nbp.pl/api/exchangerates/rates/A/USD/");
    }
}
