package xyz.blackdev.blueBerryNetworkHelper.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class MongoDBUtil {
    public static void insertDocument(String uri, String dbName, String collectionName, Document doc) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            collection.insertOne(doc);
        }
    }

    public static FindIterable<Document> findDocuments(String uri, String dbName, String collectionName) {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find();
    }
}