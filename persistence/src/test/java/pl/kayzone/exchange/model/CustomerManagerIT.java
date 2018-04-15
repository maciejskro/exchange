package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Customers;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void t00_testGetDatastore() throws Exception {
        Datastore result = customerManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }

    @Test
    public void t00_testGetMorphia() throws Exception {
        Morphia result = customerManager.getMorphia();
        Assert.assertEquals(null, result);
    }

    @Test
    public void t01_saveCustomers() {

    }

    @Test(expected = NullPointerException.class)
    public void t01_saveNullCustomers() {

    }
}
