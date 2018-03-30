package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class BaseManagerTest {


    private static final String EXCHANGEDBNAME = "exchangeOffice";
    private static final String CONNSTR = "mongodb://127.0.0.1:27017/" + EXCHANGEDBNAME;
    private BaseManager baseManager;
    private Morphia morphia;
    private MongoClient mongoClient;
    private Datastore ds;
    private Currency currency;

    @Before
    public void setupClass() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        baseManager = new BaseManager(mongoClient);

        ds = morphia.createDatastore(mongoClient,EXCHANGEDBNAME);
        currency = new Currency();
        currency.setIdCode("USD");
        currency.setName("dolar amerykański");
        currency.setRates(1.0);
        currency.setTablesType("A");
        currency.setUrlNbp("http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/");
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsNull() {
        String conn = null;

        ds.ensureIndexes();

        Datastore ds2 = baseManager.getDatastore(conn);

        assertThat(baseManager.getDatastore(conn)).isInstanceOf(Datastore.class);
        assertThat(ds2.getDB().getName()).isEqualTo(EXCHANGEDBNAME);
    }

    @Test
    public void checkIfConnectionStringIsNullThenValidConnectionIsSet() {
        String conn = null;

        baseManager.getDatastore(conn);

        assertThat(baseManager.getConnectionString()).isEqualTo(CONNSTR);
    }

    @Test
    public void checkIfConnStringIsNullThenDefaultDBName() {
        String conn = null;

        BaseManager bm = mock(BaseManager.class);
        bm.getDatastore(conn);

        verify(bm).getDatastore(nullable(String.class));
    }

    @Test
    public void checkReturnMorphia() throws Exception {

        baseManager.getDatastore(CONNSTR);

        assertThat(baseManager.getMorphia()).isEqualTo(morphia);
    }

    @Test
    public void checkIfCanSaveSimpleObject() {
        String conn = CONNSTR;

        Query<Currency> q =
                baseManager.getDatastore(null).createQuery(Currency.class);
        Currency curr = q.field("_id").equal("USD").get();
        if (curr.getIdCode() != currency.getIdCode()) {
            baseManager.getDatastore(conn).save(currency);
        }
        assertThat(baseManager.getDatastore(conn).save(currency));
    }
    @Test
    public void checkIfCanFindSavedObjectBefore()  {
        Query<Currency> q =
                baseManager.getDatastore(null).createQuery(Currency.class);
        Currency curr = q.field("_id").equal("USD").get();

        assertThat(curr.getIdCode()).isEqualTo(currency.getIdCode());
        assertThat(curr.getName()).isEqualTo(currency.getName());

    }

}
