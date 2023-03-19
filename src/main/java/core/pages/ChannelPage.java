package core.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChannelPage extends HeaderZone {

    @Getter
    //Todo: I understand that the locator in this case is so-so,
    // and if the tab is renamed / uses a different locale, then the test will break.
    // If you have a better solution please let me know.
    @FindBy(xpath = "//div[text()='Видео']")
    private WebElement videoTab;

    public ChannelPage(WebDriver webDriver) {
        super(webDriver);
        load();
    }

    /**
     * Waits until tab loaded
     * @return - current instance
     */
    @Override
    public ChannelPage load() {
        waitFor(ExpectedConditions.visibilityOf(videoTab));
        return this;
    }

    /**
     * Click video tab
     * @return - current instance
     */
    @Step("Click videoTab")
    public ChannelPage clickVideoTab() {
        videoTab.click();
        load();
        return this;
    }

    /**
     * Click video by number
     * @return - current instance
     */
    @Step("Click video by number")
    public ChannelPage clickVideoByNumber(int number) {
        number = number + 32;
        String xpathWithNumber = "(//a[@id = 'video-title-link'])[" +  number + "]";
        waitFor(ExpectedConditions.elementToBeClickable(By.xpath(xpathWithNumber)));
        webDriver.findElement(By.xpath(xpathWithNumber)).click();
        return this;
    }
}