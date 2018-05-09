package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class BaseManagerImplTest {


    private static final String EXCHANGEDBNAME = "exchangeOffice";
    private static final String CONNSTR = "mongodb://127.0.0.1:27017/" + EXCHANGEDBNAME;
    private BaseManagerImpl baseManagerImpl;
    private Morphia morphia;
    private MongoClient mongoClient;
    private Datastore ds;
    public TestClassCreator tcc;
    private Currency currency;

    @Before
    public void setupClass() {
        //morphia.mapPackage("pl.kayzone.exchange.model.entity");
        mongoClient = new MongoClient();
        morphia = new Morphia();
        //baseManagerImpl = new BaseManagerImpl(mongoClient,morphia);
        ds = morphia.createDatastore(mongoClient,EXCHANGEDBNAME);
        this.tcc  = new TestClassCreator();
        this.currency = tcc.getCurrency();
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsNull() {
        String conn = null;

        //ds.ensureIndexes();

        Datastore ds2 = baseManagerImpl.getDatastore(conn);

        assertThat(baseManagerImpl.getDatastore(conn) ).isInstanceOf(Datastore.class);
        assertThat(ds2.getDB().getName()).isEqualTo(EXCHANGEDBNAME);
    }

    @Test
    public void checkIfConnectionStringIsNullThenValidConnectionIsSet() {
        String conn = null;

        baseManagerImpl.getDatastore(conn);

        assertThat(baseManagerImpl.getConnectionString()).isEqualTo(CONNSTR);
    }

    @Test
    public void checkIfConnStringIsNullThenDefaultDBName() {
        String conn = null;

        BaseManagerImpl bm = mock(BaseManagerImpl.class);
        bm.getDatastore(conn);

        verify(bm).getDatastore(nullable(String.class));
    }

    @Test
    public void checkReturnMorphia() throws Exception {

        baseManagerImpl.getDatastore(CONNSTR);

        assertThat(baseManagerImpl.getMorphia()).isInstanceOf(Morphia.class);
    }

    @Test
    public void checkIfCanSaveSimpleObject() {
        String conn = CONNSTR;

        Query<Currency> q =
                baseManagerImpl.getDatastore(null).createQuery(Currency.class);
        //baseManagerImpl.getDatastore(conn).save(tcc.getCurrency());
        Currency curr = q.field("idCode").equal("USD").get();
        if (curr == null || currency == null || curr.getIdCode() != currency.getIdCode()) {
            baseManagerImpl.getDatastore(conn).save(currency);
        }
        assertThat(baseManagerImpl.getDatastore(conn).save(currency));
    }
    @Test
    public void checkIfCanFindSavedObjectBefore()  {
        Query<Currency> q =
                baseManagerImpl.getDatastore(null).createQuery(Currency.class);
        baseManagerImpl.getDatastore(CONNSTR).save(tcc.getCurrency());
        Currency curr = q.field("idCode").equal("USD").get();

        assertThat(curr.getIdCode()).isEqualTo(currency.getIdCode());
        assertThat(curr.getName()).isEqualTo(currency.getName());

    }
    @After
    public void cleanAllCollectionsAfterTest() {
        baseManagerImpl.getDatastore(CONNSTR).delete(tcc.getCurrency());
    }

}
