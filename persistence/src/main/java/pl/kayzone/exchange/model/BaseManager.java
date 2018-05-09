package pl.kayzone.exchange.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public interface BaseManager<T> {

    Datastore getDatastore (String conn);
    Morphia getMorphia();
    String getConnectionString();
    void save (T object);
    List<T> findAll();
    T findById( ObjectId id);
}
