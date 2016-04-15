package app.mongoservice;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoClientFactory extends AbstractMongoService {
    public MongoClientFactory() {
    }

    public MongoClient getMongoClient() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(this.getMongoProperties().getProperty("server", "host"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mongoClient;
    }
}
