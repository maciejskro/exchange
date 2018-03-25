package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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
    private Datastore ds;

    @Before
    public void setupClass() {
        this.baseManager = new BaseManager(mockMorphia, mockMongoclient);
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsNull() {
        String conn = null;
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        mockDS.ensureIndexes();
        baseManager.getDatastore(conn);
        assertThat(baseManager.getDatastore(conn)).isInstanceOf(Datastore.class);
    }

    @Test
    public void checkIfConnectionStringIsNullThenValidConnectionIsSet() {
        String conn = null;
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        baseManager.getDatastore(conn);
        assertThat(baseManager.getConnectionString()).isEqualTo(CONNSTR);
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsValid() {
        String conn = CONNSTR;
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);

        baseManager.getDatastore(conn);

        verify(mockDS).ensureIndexes();
    }
    @Test
    public  void checkIfPackageMappingIsCreated() {
        String packageString = "pl.kayzone.exchange.entity";
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);

        baseManager.getDatastore(CONNSTR);

        verify(mockMorphia).mapPackage(packageString);
    }


    @Test
    public void checkFinalMorphia() {
        Morphia mr = new Morphia();
        Morphia mockMorphia = mock(Morphia.class);
        BaseManager bm = mock(BaseManager.class);


        bm.getDatastore(CONNSTR);
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        assertThat(bm.getMorphia()).isInstanceOf(Morphia.class);
    }


    @Test
    public void shouldReturnDatastoreOnProperConnectionString() {
        String conn = "mongodb://127.0.0.1:27017/exchangeOffice";

        when(baseManager.getDatastore(conn)).thenReturn(mockDS);
        BaseManager bm = mock(BaseManager.class);

        assertThat(bm.getConnectionString()).isEqualTo(CONNSTR);
    }
}
