package pl.kayzone.exchange.model;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseManagerImpl<T> implements BaseManager<T> {
    private String connectionString = "mongodb://127.0.0.1:27017/exchangeOffice";
    private MongoClient mongo;
    protected final Morphia morphia;
    protected Datastore datastore;
    private Class<T> objcectClass;
    protected Query<T> query;

    BaseManagerImpl(final MongoClient mc ) {
        this.mongo = mc;
        this.morphia = getMorphia();
        //Type t =  getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        this.objcectClass = (Class<T>) pt.getActualTypeArguments()[0];
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
                      morphia.mapPackage("pl.kayzone.exchange.model.entity");
        return morphia;
    }

    public String getConnectionString() {

        return this.connectionString;
    }
    public   void save (T object) {
        if (object != null) {
            datastore.save(object);
        } else
            throw  new NullPointerException("no object to save");
    }
    public List<T> findAll() {
        query = this.datastore.createQuery(objcectClass);
        return query.asList();
    }

    public T findById( ObjectId id) {
       return (T) this.datastore.get(id);
    }
}

