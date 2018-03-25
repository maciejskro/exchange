package pl.kayzone.exchange.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(noClassnameStored = true)
public class DummyEntity {

    @Id
    private ObjectId id;

    @Property("s")
    private String someString;


    public DummyEntity(String someString) {
        this.someString = someString;
    }


    public ObjectId getId() {
        return id;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getSomeString() {
        return someString;
    }


    public void setSomeString(String someString) {
        this.someString = someString;
    }
}
