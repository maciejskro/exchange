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
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CurrenciesCourseManagerTest {
    @Mock
    Datastore ds;
    @Mock
    Query<CurrencyCourse> query;
    @Mock
    MongoClient mongo;
    @Mock
    Morphia morphia;
    @Mock
    Datastore datastore;
    @InjectMocks
    CurrenciesCourseManager currenciesCourseManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        currenciesCourseManager.save(new CurrencyCourse("idCode", LocalDateTime.of(2018, Month.MARCH, 30, 15, 58, 26), LocalDateTime.of(2018, Month.MARCH, 30, 15, 58, 26), new BigDecimal(0), new BigDecimal(0), Boolean.TRUE));
    }

    @Test
    public void testFindAll() throws Exception {
        List<CurrencyCourse> result = currenciesCourseManager.findAll();
        Assert.assertEquals(Arrays.<CurrencyCourse>asList(new CurrencyCourse("idCode", LocalDateTime.of(2018, Month.MARCH, 30, 15, 58, 26), LocalDateTime.of(2018, Month.MARCH, 30, 15, 58, 26), new BigDecimal(0), new BigDecimal(0), Boolean.TRUE)), result);
    }

    @Test
    public void testGetDatastore() throws Exception {
        Datastore result = currenciesCourseManager.getDatastore("conn");
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme