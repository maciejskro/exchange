package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import pl.kayzone.exchange.model.entity.CurrencyCourse;
import pl.kayzone.exchange.model.entity.Customers;
import pl.kayzone.exchange.model.entity.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionManagerIT {
    Datastore ds;
    Query<Transaction> query;
    Logger LOGG;
    MongoClient mongo;
    Morphia morphia;
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
        (new CurrenciesManager(mongo,morphia)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse().getIdCode());
        (new CurrenciesCourseManager(mongo,morphia)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse());
        (new CustomerManager(mongo,morphia)).getDs().save( t.getCustomers() );
        (new CurrenciesCourseManager(mongo,morphia)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse());
        transactionManager.save(t);


        Transaction resultT = transactionManager.getDs().createQuery(Transaction.class).filter("_id",t.getId()).get();

        assertThat(resultT).isEqualToComparingOnlyGivenFields(t,
                "customers","valueTransaction","transactionCurrencyList");

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

    @AfterClass
    public static void cleanAllDatabasesCollections() {
        Morphia mor = new Morphia();
        MongoClient monc = new MongoClient();
        TransactionManager tm = new TransactionManager(monc, mor);
        tm.getDs().delete(tm.getDs().createQuery(Transaction.class));
        CurrenciesCourseManager ccm  = new CurrenciesCourseManager(monc, mor);
        ccm.getDs().delete(ccm.getDs().createQuery(CurrencyCourse.class));
        CustomerManager cm = new CustomerManager(monc, mor);
        cm.getDs().delete(cm.getDs().createQuery(Customers.class));
    }

}
