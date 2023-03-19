package tests;

import core.config.DriverManager;
import core.matchers.SeleniumPageMatcher;
import core.pages.ChannelPage;
import core.pages.HeaderZone;
import core.pages.SearchPage;
import core.pages.StartPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Feature("Selenium task")
public class SeleniumTest extends TestBase {
    private static WebDriver webDriver;

    private static StartPage startPage;
    private SeleniumPageMatcher matcher = new SeleniumPageMatcher();
    @BeforeAll
    public static void setUp() {
        webDriver = DriverManager.getWebDriver();
        startPage = new StartPage(webDriver).open();
    }

    @AfterAll
    public static void shutDown() {
        webDriver.close();
    }
    @BeforeEach
    public void openStartPage() {
        startPage.open();
    }

    @DisplayName("Check 'test it' channel in search list ")
    @Test
    public void checkTestItChannelInSearchList() {
        new HeaderZone(webDriver).searchQueryInput("Test it");
        SearchPage searchPage = new SearchPage(webDriver);
        matcher.assertSearchPage(searchPage);
    }

    @DisplayName("Open video number 7")
    @Test
    public void openVideoNumber7() {
        new HeaderZone(webDriver).searchQueryInput("Test it");
        new SearchPage(webDriver).chanelClick();
        new ChannelPage(webDriver)
                .clickVideoTab()
                .clickVideoByNumber(7);
    }
}