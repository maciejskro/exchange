package pl.kayzone.exchange.control.helpers;

import com.mongodb.DB;
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
        assertThat(baseManager.getDatastore(conn)).isInstanceOf(Datastore.class);
    }

    @Test
    public void checkIfBuildingRightIfConnectionStringIsValid() {
        String conn = CONNSTR;
        String packageString = "pl.kayzone.exchange.entity";
        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        mockDS.ensureIndexes();
        assertThat(baseManager.getDatastore(conn)).isInstanceOf(Datastore.class);
        assertThat(baseManager.getDatastore(conn).getDB().getName()).isEqualTo(EXCHANGEDBNAME);
    }

    @Test
    public void checkFinalMorphia() {
        Morphia mr = new Morphia();
        Morphia mockMorphia = mock(Morphia.class);

        when(mockMorphia.createDatastore(mockMongoclient,EXCHANGEDBNAME)).thenReturn(mockDS);
        //assertNotEquals(mockMorphia,mr);
    }

    @Test
    public void shouldReturnDatastoreWhenNulIsGiven() {
        String conn =null;
        when(baseManager.getDatastore(conn)).thenReturn(mockDS);

        //Datastore ds = baseManager.getDatastore(conn);
        //Datastore spyDs = spy(ds);

    }

    @Test
    public void shouldReturnDatastoreOnProperConnectionString() {
        String conn = "mongodb://127.0.0.1:27017/exchangeOffice";

        this.baseManager.getDatastore(conn);

        //verify(mockDS).ensureIndexes();
        when(baseManager.getDatastore(conn)).thenReturn(mockDS);
        verify(baseManager.getDatastore(conn)).ensureIndexes();
    }
}
