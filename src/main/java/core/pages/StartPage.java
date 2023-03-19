package core.pages;

import core.config.PropertiesProvider;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartPage extends HeaderZone {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Getter
    @FindBy(id = "video-preview")
    private WebElement videoPreview;

    public StartPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Opens start page
     * @return - start page instance
     */
    @Step("Open start page")
    public StartPage open() {
        String url = PropertiesProvider.getSeleniumStartPageUrl();
        logger.info("Opening start page by url: " + url);
        webDriver.get(url);
        StartPage startPage = new StartPage(webDriver);
        return startPage;
    }

    /**
     * Waits until start page loaded
     * @return - current instance
     */
    @Override
    public StartPage load() {
        waitFor(ExpectedConditions.visibilityOf(videoPreview));
        return this;
    }
}