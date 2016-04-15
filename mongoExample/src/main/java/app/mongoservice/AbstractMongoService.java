package app.mongoservice;

import app.mongoservice.util.PropertiesHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractMongoService {
    private Properties mongoProperties;
    private final String PROPERTIES_FILE_NAME = "mongo.properties";

    protected Properties getMongoProperties() {
        mongoProperties = new Properties();
        try {
            mongoProperties.load(new FileInputStream(new PropertiesHelper().getPropertiesFile(PROPERTIES_FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mongoProperties;
    }
}
