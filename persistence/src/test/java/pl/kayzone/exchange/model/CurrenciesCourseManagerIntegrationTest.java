package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;
import pl.kayzone.exchange.model.helpers.TestClassCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrenciesCourseManagerIntegrationTest {
    Datastore ds;
    Query<CurrencyCourse> query;
    MongoClient mongo;
    Morphia morphia;
    Datastore datastore;
    CurrenciesCourseManager currenciesCourseManager;
    TestClassCreator tcc;
    CurrenciesManager cm;

    @Before
    public void setUp() {
        mongo = new MongoClient();
        morphia = new Morphia();
        currenciesCourseManager = new CurrenciesCourseManager(mongo,morphia);
        tcc = new TestClassCreator();
        cm  = new CurrenciesManager(mongo,morphia);
        cm.save(tcc.getCurrency());
    }

    @Test
    public void t1_testSave() throws Exception {
        CurrencyCourse ccm = tcc.getCurrencyCourse();
        currenciesCourseManager.save(ccm);
    }

    @Test
    public void t2_testFindAll() throws Exception {
        List<CurrencyCourse> result = currenciesCourseManager.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void t3_testFindActualCourse() throws Exception {
        CurrencyCourse result = currenciesCourseManager.findActualCourse("USD");
        System.out.println(result.getValidTo());
      //  assertThat(result.getIdCode()).isEqualTo(tcc.getCurrency());
        assertThat(result.getActive()).isEqualTo(true);
    }

    @Test
    public void testCreateOperations() throws Exception {
        UpdateOperations<CurrencyCourse> result = currenciesCourseManager.createOperations();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testUpdate() throws Exception {
        UpdateResults result = currenciesCourseManager.update(new CurrencyCourse(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)), LocalDateTime.of(2018, Month.APRIL, 2, 23, 8, 19), LocalDateTime.of(2018, Month.APRIL, 2, 23, 8, 19), new BigDecimal(0), new BigDecimal(0), Boolean.TRUE), null);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testRemove() throws Exception {
  //      currenciesCourseManager.remove(new CurrencyCourse(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)), LocalDateTime.of(2018, Month.APRIL, 2, 23, 8, 19), LocalDateTime.of(2018, Month.APRIL, 2, 23, 8, 19), new BigDecimal(0), new BigDecimal(0), Boolean.TRUE));
    }

    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = currenciesCourseManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetMorphia() throws Exception {
        Morphia result = currenciesCourseManager.getMorphia();
        Assert.assertEquals(null, result);
    }
    @AfterClass
    public static void removeAllCollections() {
        //CurrenciesManager cm = new CurrenciesManager(new MongoClient(),new Morphia());
       // cm.getDs().delete(new TestClassCreator().getCurrency());
        CurrenciesCourseManager ccm = new CurrenciesCourseManager(new MongoClient(), new Morphia());
       ccm.getDatastore(null).delete(new TestClassCreator().getCurrencyCourse());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme