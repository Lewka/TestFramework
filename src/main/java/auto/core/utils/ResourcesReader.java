package auto.core.utils;

import java.io.InputStream;
import java.util.Properties;

public class ResourcesReader {

    private static final String CAPABILITIES = "capabilities.properties";

    /**
     * Loads the properties from the given properties file.
     *
     * @param resources the name of the properties file to be processed
     * @return a {@link Properties} object containing the content of the properties file
     */
    private static Properties loadResources(String resources) {
        Properties properties = new Properties();
        try (InputStream inputStream = ResourcesReader.class.getClassLoader().getResourceAsStream(resources)) {
            properties.load(inputStream);
        } catch (Exception e) {
//            error(e.getMessage());
        }
        return properties;
    }

    public static Browser getBrowser() {
        return Browser.parse(loadResources(CAPABILITIES).getProperty("browser"));
    }
}