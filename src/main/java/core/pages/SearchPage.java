package core.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class SearchPage extends HeaderZone {

    @FindBy(xpath = "//a[@href='/@TestITTMS']")
    private WebElement avatar;

    @FindBy(xpath = "//div[@id='text-container']/*[text()='Test IT']")
    private WebElement channelName;

    @FindBy(xpath = "//span[@id='subscribers' and contains(text(),'TestITTMS')]")
    private WebElement channelUserName;

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
        load();
    }

    /**
     * Waits until avatar channel loaded
     * @return - current instance
     */
    @Override
    public SearchPage load() {
        waitFor(ExpectedConditions.visibilityOf(avatar));
        return this;
    }

    /**
     * click on channel
     * @return - current instance
     */
    @Step("Channel button click")
    public SearchPage chanelClick() {
        channelName.click();
        return this;
    }

}
