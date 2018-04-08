package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.kayzone.exchange.model.entity.Currency;

import java.util.Arrays;
import java.util.List;

//@RunWith(JUnitParamsRunner.class)
public class CurrenciesManagerTest {
    @Mock
    Datastore ds;
    @Mock
    Query<Currency> query;
    @Mock
    MongoClient mongo;
    @Mock
    Morphia morphia;
    @Mock
    Datastore datastore;
    @InjectMocks
    CurrenciesManager currenciesManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //currenciesManager = new CurrenciesManager(mongo,morphia);
    }

    @Test
    public void testSave() throws Exception {
        currenciesManager.save(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Currency> result = currenciesManager.findAll();
        Assert.assertEquals(Arrays.<Currency>asList(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0))), result);
    }

    @Test
    public void testFind() throws Exception {
        Currency result = currenciesManager.find(null);
        Assert.assertEquals(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)), result);
    }

    @Test
    public void testCreateOperations() throws Exception {
        UpdateOperations<Currency> result = currenciesManager.createOperations();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testUpdate() throws Exception {
        UpdateResults result = currenciesManager.update(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)), null);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testRemove() throws Exception {
        currenciesManager.remove(new Currency("idCode", "name", "urlNbp", "tablesType", Double.valueOf(0)));
    }

    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = currenciesManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetMorphia() throws Exception {
        Morphia result = currenciesManager.getMorphia();
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme