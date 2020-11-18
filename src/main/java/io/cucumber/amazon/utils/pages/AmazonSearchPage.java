package io.cucumber.amazon.utils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonSearchPage extends BasePage{

    private WebDriver driver;

    public AmazonSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='a-section aok-relative s-image-square-aspect']")
    private List<WebElement> foundElements;

    @FindBy(xpath = "//span[@class='a-price']")
    private List<WebElement> elementsPrices;

    public List<WebElement> getFoundElements() {
        return foundElements;
    }

    public List<WebElement> getElementsPrices() {
        return elementsPrices;
    }

    public void getFirstSearchElementAndClick() {
        getFoundElements().get(0).click();
    }

    public float getFirstElementPrice() {
        String price = getElementsPrices().get(0).getText().substring(1);
        price = price.replace("\n", ".");

        return Float.parseFloat(price);
    }
}
