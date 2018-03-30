package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class  BaseManager {
    protected String connectionString = "mongodb://127.0.0.1:27017/exchangeOffice";
    protected MongoClient mongo;
    protected Morphia morphia;
    protected Datastore datastore;

    public BaseManager( MongoClient mc) {
        this.mongo = mc;
        this.morphia = getMorphia();
        String packageName = "pl.kayzone.exchange.model.entity";
        this.morphia.mapPackage(packageName);
    }

    public Datastore getDatastore (String conn) {
        String dbname;
        if (datastore == null) {
            if (conn != null) {
                this.connectionString = conn;
                dbname = connectionString.substring(connectionString.lastIndexOf("/") + 1, connectionString.length());
            } else {
                dbname = "exchangeOffice";
            }
            datastore = morphia.createDatastore(mongo, dbname);
            datastore.ensureIndexes();
            return  datastore;
        } else
            return datastore;
    }

    public Morphia getMorphia() {
        final Morphia morphia = new Morphia();
        String packageName = "pl.kayzone.exchange.model.entity";
        morphia.mapPackage(packageName);
        return morphia;
    }

    public String getConnectionString() {
        return this.connectionString;
    }


}

