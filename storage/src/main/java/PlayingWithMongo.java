import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.inc;

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

        System.out.println(collection.find().first().toJson());

        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }

        collection.insertMany(documents);

        System.out.println(collection.count());

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        Document myDoc = collection.find(eq("i", 3)).first();
        System.out.println("toString() as BSON: ");
        System.out.println(myDoc);
        System.out.println("toString() as JSON: ");
        System.out.println(myDoc.toJson());

        System.out.println("Block:");

        collection.find(lt("i", 30)).forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println("This document is for lt: " + document);
            }
        });

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };

        collection.find(gt("i", 50)).forEach(printBlock);


        collection.find(and(gte("i", 50), lte("i", 70))).forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.print(document.get("i").toString() + ": ");
                System.out.print(Integer.valueOf(document.get("i").toString()) * Integer.valueOf(document.get("i").toString()));
                System.out.println();
            }
        });

        collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 777)));

        System.out.println(collection.find(eq("i", 777)).first().toJson());

        UpdateResult result = collection.updateMany(and(gte("i", 80), lt("i", 90)), inc("i", 100));
        System.out.println("UpdateResult: " + result.getModifiedCount());

        System.out.println(collection.count());
        collection.deleteMany(lt("i", 10));
        System.out.println(collection.count());

        collection.createIndex(new Document("i", 1));

        mongoClient.close();
    }
}