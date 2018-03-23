package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class BaseManager {
    private String connectionString = "mongodb://127.0.0.1:27017/exchangeOffice";
    private MongoClient mongo;
    protected Datastore datastore;
    private final Morphia morphia = new Morphia();

    public BaseManager() {
        this.mongo = new MongoClient();
    }

    public BaseManager (String conn) {
        this();
        String dbname;
        if (conn != null) {
            this.connectionString = conn;
            dbname = connectionString.substring(connectionString.lastIndexOf("/")+1 , connectionString.length());
        } else {
            dbname = "exchangeOffice";
        }
        this.datastore = morphia.createDatastore(mongo,dbname);
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public String getConnectionString() {
        return connectionString;
    }
}

