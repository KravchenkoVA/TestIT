package core.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class HeaderZone extends PageBase {

    @FindBy(xpath = "//div[@id='start']//*[@id='logo']")
    private WebElement logotype;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchInput;

    public HeaderZone(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Waits until header zone loaded
     * @return - current instance
     */
    @Override
    public HeaderZone load() {
        waitFor(ExpectedConditions.visibilityOf(logotype));
        return this;
    }

    /**
     * Click on logotype
     * @return - current instance
     */
    @Step("Logotype click")
    public HeaderZone logotypeClick() {
        logotype.click();
        return this;
    }

    /**
     * Type text on search query
     * @return - current instance
     */
    @Step("Press the search query")
    public HeaderZone searchQueryInput(String query) {
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }
}
