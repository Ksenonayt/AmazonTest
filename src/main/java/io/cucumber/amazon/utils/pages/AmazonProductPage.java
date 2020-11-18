package io.cucumber.amazon.utils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonProductPage extends BasePage {

    private WebDriver driver;

    public AmazonProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "price_inside_buybox")
    private WebElement price;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    public float getPrice() {
        return Float.parseFloat(price.getText().substring(1));
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }
}
