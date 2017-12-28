import co.axelrod.hellgile.management.Project;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 25.12.2017.
 */
public class PlayingWithMongoPOJO {
    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient(
                new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase database = mongoClient.getDatabase("HellgileMongo").withCodecRegistry(pojoCodecRegistry);

        MongoCollection<PlainOldJavaObject> collection = database.getCollection("projects", PlainOldJavaObject.class);

        // drop all the data in it
        collection.drop();

        PlainOldJavaObject plainOldJavaObject = new PlainOldJavaObject();
        plainOldJavaObject.setName("Something");
        plainOldJavaObject.setPrice(1000);

        collection.insertOne(plainOldJavaObject);

        Block<PlainOldJavaObject> printBlock = new Block<PlainOldJavaObject>() {
            @Override
            public void apply(PlainOldJavaObject PlainOldJavaObject) {
                System.out.println(PlainOldJavaObject.toString());
            }
        };

        collection.find().forEach(printBlock);

        mongoClient.close();
    }
}