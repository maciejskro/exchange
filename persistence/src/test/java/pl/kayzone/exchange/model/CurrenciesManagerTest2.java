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
import pl.kayzone.exchange.model.entity.Currency;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CurrenciesManagerTest2 {
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
    public void testGetDatastore() throws Exception {
        Datastore result = currenciesManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme