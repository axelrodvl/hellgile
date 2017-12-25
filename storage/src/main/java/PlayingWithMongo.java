import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 25.12.2017.
 */
public class PlayingWithMongo {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase database = mongoClient.getDatabase("HellgileMongo");

        MongoCollection<Document> collection = database.getCollection("Games");

        // drop all the data in it
        collection.drop();

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);

        System.out.println(collection.count());

        mongoClient.close();
    }
}
