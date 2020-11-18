package io.cucumber.amazon.utils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonCartPage extends BasePage{

    private WebDriver driver;

    public AmazonCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name = "quantity")
    private List<WebElement> listOfQuantityDropdownElements;

    @FindBy(id = "sc-subtotal-label-activecart")
    private WebElement subtotalText;

    public List<WebElement> getListOfQuantityDropdownElements() {
        return listOfQuantityDropdownElements;
    }

    public void getQuantityByValueAndSetNewOne(String oldValue, int index, double calculatedSubtotal) {
        for (WebElement we : getListOfQuantityDropdownElements()) {
            Select select = new Select(we);
            String selected = select.getFirstSelectedOption().getText();
            if (selected.contains(oldValue)) {
                setQuantityToSelectedItem(index, we, calculatedSubtotal);
            }
        }
    }

    public void setQuantityToSelectedItem(int index, WebElement we, double calculatedSubtotal) {
        while ((int)getSubtotalValue() > (int)calculatedSubtotal) {
            Select select = new Select(we);
            select.selectByIndex(index + 1);
            select.selectByIndex(index);
            waitUntilSubtotalTextChanged(we);
        }
    }

    public void waitUntilSubtotalTextChanged(WebElement we) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementValue(we, "Subtotal (2 items)"));
        }
        catch (Exception e) {
        }
    }
}
