package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainer;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kayzone.exchange.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionManagerImpl extends BaseManagerImpl<Transaction> implements TransactionManager{

    private final Datastore ds;
    private Query<Transaction> query;
    private static final Logger LOGG = LoggerFactory.getLogger(CurrenciesManagerImpl.class);

    public TransactionManagerImpl(MongoClient mc) {
        super(mc);
        this.ds= super.getDatastore("exchangeOffice");
        query = super.getDatastore(getConnectionString()).createQuery(Transaction.class);
    }
    public void save(Transaction trans) {
        super.save(trans);
    }

    public Datastore getDs() {
        return this.ds;
    }

    public List<Transaction> findAll() {
        return getDs().find(Transaction.class).asList();
    }

    public List<Transaction> findPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.query = getDs().createQuery(Transaction.class);
        CriteriaContainer criteria = query.and(
                query.criteria("transactionTime").greaterThanOrEq(startDate),
                query.criteria("transactionTime").lessThanOrEq(endDate) );
        List<Transaction> list = query.asList();
        return list;
    }

}
