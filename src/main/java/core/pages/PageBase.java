package core.pages;

import io.qameta.allure.Step;
import lombok.Setter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public abstract class PageBase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WebDriver webDriver;

    @Setter
    public int waitingTime = 10; // seconds

    public PageBase(WebDriver webDriver) {
        this.webDriver = webDriver;
        init();
    }

    /**
     * Call to initialize web elements
     */
    protected void init() {
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Waits for specified conditions
     * @param waitConditions - waiting conditions
     */
    protected void waitFor(Function waitConditions) {
        new WebDriverWait(webDriver, waitingTime).until(waitConditions);
    }

    /**
     * Jump to specified url
     * @param url - target url
     * @return - current instance
     */
    @Step("Jump to url: {0}")
    public PageBase jumpTo(String url) {
        webDriver.navigate().to(url);
        return this;
    }

    /**
     * Waits until page loaded
     * @return - current instance
     */
    @Step("Waiting for page load")
    public abstract PageBase load();

    /**
     * Checks element's presents
     * @param webElement - target element
     * @return - true of false
     */
    public boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
