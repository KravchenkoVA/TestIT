package core.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverManager {
    private static Logger logger = LoggerFactory.getLogger(DriverManager.class);

    private static volatile DriverManager instance;

    private WebDriver webDriver;

    private DriverManager() {
        logger.debug("Initializing web driver");
        initWebDriver(PropertiesProvider.getSeleniumBrowser());
    }
    /**
     * Singleton
     * @return - instance
     */
    private static DriverManager getInstance() {
        DriverManager localInstance = instance;
        if (localInstance == null) {
            synchronized (DriverManager.class) {
                localInstance = instance;
                if (instance == null) {
                    localInstance = instance = new DriverManager();
                }
            }
        }
        return localInstance;
    }

    public static WebDriver getWebDriver() {
        return getInstance().webDriver;
    }

    /**
     * Web driver initialization
     * @param selectedBrowser - browser name from properties, that will be used in tests
     */
    private void initWebDriver(String selectedBrowser) {
        UsingBrowser usingBrowser = UsingBrowser.valueOf(selectedBrowser);
        switch (usingBrowser) {
            case CHROME: {
                //TODO: if you need other version, change or remove
                WebDriverManager.chromedriver().version("110.0.5481.77");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            }
            default: {
                throw new IllegalArgumentException("Failed to initialize web driver for browser with name " +
                        "\"" + selectedBrowser + "\". Use one of those: " + UsingBrowser.values());
            }
        }
    }
}