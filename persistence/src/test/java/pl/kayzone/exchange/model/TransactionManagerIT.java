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
import org.slf4j.Logger;
import pl.kayzone.exchange.model.entity.Transaction;

public class TransactionManagerIT {
    @Mock
    Datastore ds;
    @Mock
    Query<Transaction> query;
    @Mock
    Logger LOGG;
    @Mock
    MongoClient mongo;
    @Mock
    Morphia morphia;
    @Mock
    Datastore datastore;
    @InjectMocks
    TransactionManager transactionManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        transactionManager.save(new Transaction());
    }

    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = transactionManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetMorphia() throws Exception {
        Morphia result = transactionManager.getMorphia();
        Assert.assertEquals(null, result);
    }

}
