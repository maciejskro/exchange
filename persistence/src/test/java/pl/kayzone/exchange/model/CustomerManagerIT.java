package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import pl.kayzone.exchange.model.entity.Customers;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerManagerIT {
    Datastore ds;
    Query<Customers> query;
    MongoClient mongo;
    Morphia morphia;
    Datastore datastore;
    CustomerManager customerManager;
    TestClassCreator tcc;

    @Before
    public void setUp() {
        this.mongo = new MongoClient();
        this.morphia = new Morphia();
        customerManager = new CustomerManager(mongo, morphia);
        this.tcc = new TestClassCreator();
    }

    @Test
    public void t00_testGetDatastore() throws Exception {
        Datastore result = customerManager.getDatastore("conn");
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Datastore.class);
    }

    @Test
    public void t00_testGetMorphia() throws Exception {
        Morphia result = customerManager.getMorphia();
       assertThat(result).isNotNull();
       assertThat(result).isInstanceOf(Morphia.class);
    }

    @Test
    public void t01_saveCustomers() {
        Customers c= tcc.getCustomers();
        customerManager.save(c);


    }

    @Test(expected = NullPointerException.class)
    public void t01_saveNullCustomers() {
        customerManager.save(null);

    }
}
