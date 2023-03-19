package core.matchers;

import core.pages.SearchPage;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumPageMatcher {

    /**
     * Checks search page
     * @param page - page
     */
    @Step("Check start page")
    public void assertSearchPage(SearchPage page) {
        assertTrue(page.isElementPresent(page.getAvatar()), "Avatar should present");
        assertTrue(page.isElementPresent(page.getChannelName()), "Channel name should present");
        assertTrue(page.isElementPresent(page.getChannelUserName()), "Channel username should present");
    }
}
