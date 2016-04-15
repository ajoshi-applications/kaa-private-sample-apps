package app.mongoservice.util;

import java.io.File;

public class PropertiesHelper {
    public File getPropertiesFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
