package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Customers;

public class CustomerManagerIT {
    Datastore ds;
    Query<Customers> query;
    MongoClient mongo;
    Morphia morphia;
    Datastore datastore;
    CustomerManager customerManager;

    @Before
    public void setUp() {

        customerManager = new CustomerManager(new MongoClient(), new Morphia());
    }

    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = customerManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetMorphia() throws Exception {
        Morphia result = customerManager.getMorphia();
        Assert.assertEquals(null, result);
    }
}
