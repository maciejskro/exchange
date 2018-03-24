package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class BaseManagerTest {


    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    private BaseManager baseManager;
    @Mock private Morphia mockMorphia;
    @Mock private MongoClient mockMongoclient;
    @Mock private Datastore mockDS;


    @Before
    public void setupClass() {
        this.baseManager = new BaseManager(mockMorphia, mockMongoclient);
    }

    @Test
    public void checkIfBuildingRight() {
        String conn = null;
        String packageString = "pl.kayzone.exchange.entity";
        this.baseManager.getDatastore(conn);
        verify(baseManager.morphia).mapPackage(packageString);
    }

    @Test
    public void checkFinalMorphia() {
        Morphia mr = new Morphia();
        Morphia mockMorphia = mock(Morphia.class);

        when(mockMorphia.createDatastore(mockMongoclient,"string")).thenReturn(mockDS);
        assertNotEquals(mockMorphia,mr);
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
