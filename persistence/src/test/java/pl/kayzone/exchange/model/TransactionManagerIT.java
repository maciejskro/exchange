package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import pl.kayzone.exchange.model.entity.CurrencyCourse;
import pl.kayzone.exchange.model.entity.Customers;
import pl.kayzone.exchange.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionManagerIT {
    Datastore ds;
    Query<Transaction> query;
    Logger LOGG;
    MongoClient mongo;
    Morphia morphia;
    TransactionManagerImpl transactionManager;
    TestClassCreator tcc;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        mongo = new MongoClient();
        morphia = new Morphia();
        transactionManager = new TransactionManagerImpl(mongo);
        tcc = new TestClassCreator();
        ds = transactionManager.getDs();

    }

    @Test
    public void t01_testSave() throws Exception {
        Transaction t = tcc.getTransaction();
        (new CurrenciesManagerImpl(mongo,morphia)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse().getIdCode());
        (new CurrenciesCourseManagerImpl(mongo)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse());
        (new CustomerManagerImpl(mongo,morphia)).getDs().save( t.getCustomers() );
        (new CurrenciesCourseManagerImpl(mongo)).getDs().save(t.getTransactionCurrencyList().get(0).getCurrencyCourse());
        transactionManager.save(t);


        Transaction resultT = transactionManager.getDs().createQuery(Transaction.class).filter("_id",t.getId()).get();

        assertThat(resultT).isEqualToComparingOnlyGivenFields(t,
                "customers","valueTransaction");

    }

    @Test(expected = NullPointerException.class)
    public void t02_testSaveWithNull() {

        List<Transaction> listaTransBefore = transactionManager.getDs().createQuery(Transaction.class).asList();
        transactionManager.save(null);
        List<Transaction> listaTransAfter = transactionManager.getDs().createQuery(Transaction.class).asList();

        assertThat(listaTransAfter.size()).isEqualTo(listaTransBefore.size());
    }

    @Test
    public void t03_testFindAllTransaction() {
        List<Transaction> list = transactionManager.findAll();

        assertThat(list).isNotNull();
        assertThat(list.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void t03_testFindTransBetweenDate() {
        List<Transaction> lista = transactionManager.findPeriod(LocalDateTime.now().minusDays(5), LocalDateTime.now() );
        assertThat(lista).isNotNull();
        assertThat(lista.size()).isGreaterThanOrEqualTo(1);
        assertThat(lista).hasAtLeastOneElementOfType(Transaction.class);
        assertThat(lista.get(0)).isEqualToComparingOnlyGivenFields(tcc.getTransaction(),
                "valueTransaction");
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
        TransactionManagerImpl tm = new TransactionManagerImpl(monc);
        tm.getDs().delete(tm.getDs().createQuery(Transaction.class));
        CurrenciesCourseManagerImpl ccm  = new CurrenciesCourseManagerImpl(monc);
        ccm.getDs().delete(ccm.getDs().createQuery(CurrencyCourse.class));
        CustomerManagerImpl cm = new CustomerManagerImpl(monc, mor);
        cm.getDs().delete(cm.getDs().createQuery(Customers.class));
    }

}
