package pl.kayzone.exchange.model;

import org.bson.types.ObjectId;
import pl.kayzone.exchange.model.entity.Customers;

import java.util.List;

public interface CustomerManager extends BaseManager<Customers> {

    void save(Customers c);
    List<Customers> findAll();
    Customers find(ObjectId id);
}
