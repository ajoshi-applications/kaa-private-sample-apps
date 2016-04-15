package app.mongoservice;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MongoLogs extends AbstractMongoService {
    private MongoClientFactory factory = new MongoClientFactory();
    private DB db;

    public MongoLogs() {
        MongoClient client = factory.getMongoClient();
        db = client.getDB(this.getMongoProperties().getProperty("dbname"));
    }

    private List<String> getLogsCollections() {
        Set<String> logsCollections = db.getCollectionNames();
        List<String> result = new ArrayList<String>();
        for (String logsCollection : logsCollections) {
            if (logsCollection.contains("logs")) {
                result.add(logsCollection);
            }
        }
        return result;
    }

    private String getCurrentLogCollection() {
        List<String> collections = getLogsCollections();
        if (!collections.isEmpty()) {
            System.out.println("Enter number of log collection to show: ");
            for (int i = 0; i < collections.size(); i++) {
                System.out.println(i + 1 + ". " + collections.get(i));
            }
            int currentLogCollectionNumber = -1;
            try {
                currentLogCollectionNumber = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return collections.get(currentLogCollectionNumber - 1);
        }
        System.out.println("No collections");
        return null;
    }

    public void getLogRecords() {
        String collectionName = getCurrentLogCollection();
        if (collectionName != null) {
            DBCollection collection = db.getCollectionFromString(collectionName);
            DBCursor cursor = collection.find();
            if (!cursor.hasNext()) {
                System.out.println("Empty collection!");
            } else {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next());
                }
            }
        }
    }
}
