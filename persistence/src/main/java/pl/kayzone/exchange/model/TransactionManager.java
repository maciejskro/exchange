package pl.kayzone.exchange.model;

import pl.kayzone.exchange.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionManager extends BaseManager<Transaction> {

    void save(Transaction t);
    List<Transaction> findAll();
    List<Transaction> findPeriod(LocalDateTime startDate, LocalDateTime endDate);
}
