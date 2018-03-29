package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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

    @Before
    public void setupClass() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        baseManager = new BaseManager(morphia, mongoClient);

        ds = morphia.createDatastore(mongoClient,EXCHANGEDBNAME);
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsNull() {
        String conn = null;

        ds.ensureIndexes();

        baseManager.getDatastore(conn);

        assertThat(baseManager.getDatastore(conn)).isInstanceOf(Datastore.class);
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
    public void checkIfEnsureIndexesIsCalled() {
        String conn = CONNSTR;

        baseManager.getDatastore(conn);

        verify(ds).ensureIndexes();
    }
    @Test
    public  void checkIfPackageMappingIsCreated() throws  Exception{
        String packageString = "pl.kayzone.exchange.entity";

        baseManager.getDatastore(CONNSTR);

        verify(morphia).mapPackage(packageString);
    }

    @Test
    public void checkReturnMorphia() throws Exception {
        BaseManager bm = mock(BaseManager.class);

        bm.getDatastore(CONNSTR);

        when(bm.getMorphia()).thenReturn(morphia);

        assertThat(bm.getMorphia()).isEqualTo(morphia);
    }


    @Test
    public void shouldReturnMorphiaObject() throws Exception {
        BaseManager bm = mock(BaseManager.class);

        bm.getMorphia();

        assertThat(baseManager.getMorphia()).isSameAs(morphia);

    }
    @After
    public void tearDown() throws Exception {
        reset(morphia,mongoClient,ds);

    }
}
