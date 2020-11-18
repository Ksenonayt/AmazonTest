package io.cucumber.amazon.utils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='nav-search-field ']/input")
    private WebElement searchTextField;

    @FindBy(id = "nav-search-submit-text")
    private WebElement searchButton;

    @FindBy(id = "nav-cart")
    private WebElement cartButton;

    @FindBy(name = "quantity")
    private WebElement quantity;

    @FindBy(id = "sc-subtotal-amount-activecart")
    private WebElement subtotalValue;

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSearchTextField() {
        return searchTextField;
    }

    public WebElement getCartButton() {
        return cartButton;
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }

    public WebElement getQuantity() {
        return quantity;
    }

    public float getSubtotalValue() {
        return Float.parseFloat(subtotalValue.getText().substring(1));
    }

    public void setTextToSearchField(String text) {
        getSearchTextField().sendKeys(text);
    }

    public void clickCartButton() {
        getCartButton().click();
    }

    public void setQuantity(String value, WebElement quantity) {
        Select select = new Select(quantity);
        select.selectByValue(value);
    }
}
