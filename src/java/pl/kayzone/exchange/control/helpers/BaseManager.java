package pl.kayzone.exchange.control.helpers;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class BaseManager {
    private String connectionString = "mongodb://127.0.0.1:27017/exchangeOffice";
    private MongoClient mongo;
    protected Datastore datastore;
    protected final Morphia morphia;

    public BaseManager(Morphia m, MongoClient mc) {
        this.mongo = mc;
        this.morphia = m;
        String packageName = "pl.kayzone.exchange.entity";
        this.morphia.mapPackage(packageName);
    }

    public Datastore getDatastore (String conn) {
        String dbname;
        if (conn != null) {
            this.connectionString = conn;
            dbname = connectionString.substring(connectionString.lastIndexOf("/")+1 , connectionString.length());
        } else {
            dbname = "exchangeOffice";
        }
        datastore = morphia.createDatastore(mongo,dbname);
        datastore.ensureIndexes();
        return datastore;
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public String getConnectionString() {
        return connectionString;
    }
}

