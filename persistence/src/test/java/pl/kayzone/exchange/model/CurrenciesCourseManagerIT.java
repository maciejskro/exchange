package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrenciesCourseManagerIT {
    Datastore ds;
    Query<CurrencyCourse> query;
    MongoClient mongo;
    Morphia morphia;
    Datastore datastore;
    CurrenciesCourseManagerImpl currenciesCourseManager;
    TestClassCreator tcc;
    CurrenciesManagerImpl cm;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        mongo = new MongoClient();
        morphia = new Morphia();
        currenciesCourseManager = new CurrenciesCourseManagerImpl(mongo);
        tcc = new TestClassCreator();
        cm = new CurrenciesManagerImpl(mongo);
        datastore = cm.getDatastore("mongodb://127.0.0.1:27017/exchangeOffice");
        cm.save(tcc.getCurrency());
    }

    @Test
    public void t01_testSave() throws Exception {
        CurrencyCourse cc = tcc.getCurrencyCourse();
        cc.setIdCode(cm.find("USD"));
        currenciesCourseManager.save(cc);
        CurrencyCourse result = currenciesCourseManager.findActualCourse(cc.getIdCode().getIdCode());

        assertThat(result).isInstanceOf(CurrencyCourse.class);
        assertThat(result).isEqualTo(cc);
    }

    @Test(expected = NullPointerException.class)
    public void t02_testAnNullObject() {
        CurrencyCourse cc = null;
        currenciesCourseManager.save(cc);
        // expacted exception
    }

    @Test(timeout = 300)
    public void t02_testFindAll() throws Exception {
        List<CurrencyCourse> result = currenciesCourseManager.findAll();

        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test(timeout = 300)
    public void t03_testFindActualCourse() throws Exception {
        CurrencyCourse result = currenciesCourseManager.findActualCourse("USD");
        assertThat(result.getActive()).isEqualTo(true);
        assertThat(result).isEqualToComparingOnlyGivenFields(tcc.getCurrencyCourse()
                , "idCode.idCode", "idCode.name", "bid", "ask", "active");
    }

    @Test(timeout = 300)
    public void t10_testUpdate() throws Exception {

        currenciesCourseManager.save(tcc.getCurrencyCourse());

        List<CurrencyCourse> lista = currenciesCourseManager.getDs().createQuery(CurrencyCourse.class).asList();

        Query<CurrencyCourse> query = currenciesCourseManager.getDatastore(null).createQuery(CurrencyCourse.class)
                .field("_id").equal(lista.get(0).getId());

        UpdateOperations<CurrencyCourse> update = currenciesCourseManager.createOperations().inc("bid").inc("ask");
        UpdateResults result = currenciesCourseManager.update(query, update);

        Assert.assertEquals(result.getUpdatedCount(), 1);

    }

    @Test(timeout = 300)
    public void t15_testRemove() throws Exception {
        Query<CurrencyCourse> query = currenciesCourseManager.getDs().find(CurrencyCourse.class);
        currenciesCourseManager.remove(query.get());
        currenciesCourseManager.getDs();
        assertThat(query.asList()).hasOnlyElementsOfType(CurrencyCourse.class);
    }

    @Test(timeout = 300)
    public void t05_testGetDatastore() throws Exception {
        Datastore result = currenciesCourseManager.getDatastore("conn");
        assertThat(result).isInstanceOf(Datastore.class);
    }

    @Test(timeout = 300)
    public void t04_testGetMorphia() throws Exception {
        Morphia result = currenciesCourseManager.getMorphia();
        assertThat(result).isInstanceOf(Morphia.class);
    }

    @AfterClass
    public static void cleanAllDatabasesCollections() {
        CurrenciesCourseManagerImpl ccm = new CurrenciesCourseManagerImpl(new MongoClient());
        ccm.getDatastore(null).delete(ccm.getDs().createQuery(CurrencyCourse.class));
    }
}

