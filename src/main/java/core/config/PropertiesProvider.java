package core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class PropertiesProvider {
    private static Logger logger = LoggerFactory.getLogger(PropertiesProvider.class);

    private Properties config;
    private static volatile PropertiesProvider instance;

    /** Property name: using selenium browser*/
    private static final String SELENIUM_BROWSER_PROPERTY_NAME = "selenium.browser";

    private static final String SELENIUM_START_PAGE_URL_PROPERTY_NAME = "selenium.start.page.url";

    private PropertiesProvider() {
        config = new Properties();
        try {
            getConfig();
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    private void getConfig() throws IOException {
        try (BufferedReader configPropertiesReader = new BufferedReader(new InputStreamReader(getClass()
                .getResourceAsStream("/config.properties"), StandardCharsets.UTF_8))) {
            config.load(configPropertiesReader);
        }
    }

    /**
     * Singleton
     * @return - instance
     */
    private static PropertiesProvider getInstance() {
        PropertiesProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (PropertiesProvider.class) {
                localInstance = instance;
                if (instance == null) {
                    localInstance = instance = new PropertiesProvider();
                }
            }
        }
        return localInstance;
    }

    /**
     * Gets using browser for selenium tests
     * @return using browser name
     */
    public static String getSeleniumBrowser() {
        return getPropertyValue(SELENIUM_BROWSER_PROPERTY_NAME);
    }

    /**
     * Gets start page url for selenium tests
     * @return start page url
     */
    public static String getSeleniumStartPageUrl() {
        return getInstance().config.getProperty(SELENIUM_START_PAGE_URL_PROPERTY_NAME);
    }

    /**
     * Try to get property from environment variable
     * If fail, get property from config.properties
     * @param propertyName - needed property name
     * @return - property value
     */
    private static String getPropertyValue(String propertyName) {
        String value = System.getenv(propertyName);
        if (value == null) {
            logger.debug("Property \"" + propertyName + "\" is absent in environment variables");
            return getInstance().config.getProperty(propertyName);
        }
        return value;
    }
}