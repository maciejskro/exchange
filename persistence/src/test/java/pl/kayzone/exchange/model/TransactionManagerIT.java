package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import pl.kayzone.exchange.model.entity.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionManagerIT {
    Datastore ds;
    Query<Transaction> query;
    Logger LOGG;
    MongoClient mongo;
    Morphia morphia;
    Datastore datastore;
    TransactionManager transactionManager;
    TestClassCreator tcc;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        mongo = new MongoClient();
        morphia = new Morphia();
        transactionManager = new TransactionManager(mongo,morphia);
        tcc = new TestClassCreator();
        ds = transactionManager.getDs();
    }

    @Test
    public void t01_testSave() throws Exception {
        Transaction t = tcc.getTransaction();
        transactionManager.save(t);
    }

    @Test(expected = NullPointerException.class)
    public void t02_testSaveWithNull() {
        transactionManager.save(null);
    }


    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = transactionManager.getDatastore("conn");
        assertThat(result).isInstanceOf(Datastore.class);
    }

    @Test
    public void testGetMorphia() throws Exception {
        Morphia result = transactionManager.getMorphia();
        assertThat(result).isInstanceOf(Morphia.class);
    }

}
