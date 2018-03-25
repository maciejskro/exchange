package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import pl.kayzone.exchange.entity.DummyEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class BaseManagerTest {


    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private static final String EXCHANGEDBNAME = "exchangeOffice";
    private static final String CONNSTR = "mongodb://127.0.0.1:27017/" + EXCHANGEDBNAME;
    private BaseManager baseManager;
    @Mock private Morphia mockMorphia;
    @Mock private MongoClient mockMongoclient;
    @Mock private Datastore mockDS;

    @Before
    public void setupClass() {
        baseManager = new BaseManager(mockMorphia, mockMongoclient);
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        mockMorphia.map(DummyEntity.class);
       // when(baseManager.getDatastore(CONNSTR)).thenReturn(mockDS);
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsNull() {
        String conn = null;

        mockDS.ensureIndexes();

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

        Mockito.verify(bm).getDatastore(nullable(String.class));
    }


    @Test
    public void checkIfEnsureIndexesIsCalled() {
        String conn = CONNSTR;

        baseManager.getDatastore(conn);

        verify(mockDS).ensureIndexes();
    }
    @Test
    public  void checkIfPackageMappingIsCreated() throws  Exception{
        String packageString = "pl.kayzone.exchange.entity";

        baseManager.getDatastore(CONNSTR);

        verify(mockMorphia).mapPackage(packageString);
    }

    @Test
    public void checkReturnMorphia() throws Exception {
        BaseManager bm = mock(BaseManager.class);

        bm.getDatastore(CONNSTR);

        when(bm.getMorphia()).thenReturn(mockMorphia);

        assertThat(bm.getMorphia()).isEqualTo(mockMorphia);
    }


    @Test
    public void shouldReturnMorphiaObject() throws Exception {
        BaseManager bm = mock(BaseManager.class);

        bm.getMorphia();

        assertThat(baseManager.getMorphia()).isSameAs(mockMorphia);

    }
    @After
    public void tearDown() throws Exception {
        reset(mockMorphia,mockMongoclient,mockDS);
    }
}
