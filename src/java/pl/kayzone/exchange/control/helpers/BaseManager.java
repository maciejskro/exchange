package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class BaseManager {
    private String connectionString;
    private MongoClient mongo;
    protected Datastore datastore;
    private final Morphia morphia = new Morphia();

    public BaseManager() {
        this.mongo = new MongoClient();
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public Morphia getMorphia() {
        return morphia;
    }
}

