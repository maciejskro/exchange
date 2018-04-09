package pl.kayzone.exchange.model.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kayzone.exchange.model.helper.TestClassCreator;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
    @Mock
    Customers customers;
    @Mock
    BigDecimal valueTransaction;
    @Mock
    List<TransactionCurrency> transactionCurrencyList;
    //Field transactionTime of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field id of type ObjectId - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    Transaction transaction;
    TestClassCreator tcc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tcc = new TestClassCreator();
    }

    @Test
    public void testEquals() throws Exception {
        boolean result = transaction.equals(null);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testEqualsToNewSimilarObject() {
        Transaction t = tcc.getTransaction();

        t.equals(tcc.getTransaction());

        assertThat(t).isNotEqualTo(tcc.getTransaction());
    }

    @Test
    public void testHashCode() throws Exception {
        int result = transaction.hashCode();
        assertThat(transaction.hashCode()).isNotNull();
        assertThat(transaction.hashCode()).isEqualTo(result);
    }

    @Test
    public void testListOfTransactions() {
        Transaction t = tcc.getTransaction();

        assertThat(t).isNotNull();
        assertThat(t.getTransactionCurrencyList()).hasOnlyElementsOfTypes(TransactionCurrency.class);
        assertThat(t.getTransactionCurrencyList().size()).isGreaterThanOrEqualTo(1);
    }
}

