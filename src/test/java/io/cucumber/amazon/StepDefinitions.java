package io.cucumber.amazon;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.google.common.math.DoubleMath.fuzzyEquals;
import static io.cucumber.amazon.Hooks.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefinitions {

    private double womenHatPriceProduct;
    private double womenHatPriceSearch;
    private double manHatPriceProduct;
    private double manHatPriceSearch;
    private double calculatedSubtotal2Items;
    private double subtotalValueFinal;
    private static final double TOLERANCE = 0.00001;

    @Given("Search for {string}")
    public void SearchForValue(String searchText) {
        amazonSearchPage.setTextToSearchField(searchText);
        amazonSearchPage.clickSearchButton();
    }

    @When("Take price from search page and go to product page")
    public void takePriceGoToProductPage() {
        womenHatPriceSearch = amazonSearchPage.getFirstElementPrice();
        amazonSearchPage.getFirstSearchElementAndClick();
        womenHatPriceProduct = amazonProductPage.getPrice();
    }

    @When("Validate if prices from search page and product page are the same")
    public void validatePricesForWomanHat() {
        assertThat("Prices on search page and on product page are equals",
                womenHatPriceSearch == womenHatPriceProduct);
    }

    @When("Add product to cart and go to cart")
    public void addToCart() {
        amazonProductPage.clickAddToCartButton();
        amazonProductPage.clickCartButton();
    }

    @Then("Check if subtotal value correct for single product")
    public void checkPrice() {
        double subtotal = amazonCartPage.getSubtotalValue();
        assertThat("Subtotal value for three items is correct",
                (subtotal == womenHatPriceProduct));
    }

    @When("Take price for man hat from search page and go to product page")
    public void addToCartMultipleQuantity() {
        manHatPriceSearch = amazonSearchPage.getFirstElementPrice();
        amazonSearchPage.getFirstSearchElementAndClick();
        manHatPriceProduct = amazonProductPage.getPrice();
    }

    @When("Validate if prices from search page and product page are the same for two man hats")
    public void validatePricesForManHats() {
        assertThat("Prices on search page and on product page are equals",
                manHatPriceSearch == manHatPriceProduct);
    }

    @When("Set product quantity to {string}, add to cart and go to cart")
    public void setQuantityAndGoToCart(String quantity) {
        amazonProductPage.setQuantity(quantity, amazonProductPage.getQuantity());
        amazonProductPage.clickAddToCartButton();
        amazonProductPage.clickCartButton();
    }

    @Then("Check if subtotal value correct for {int} same hats")
    public void checkPriceMultipleQuantity(int number) {
        double subtotalValueForItemsInCart = amazonCartPage.getSubtotalValue();
        assertThat("Subtotal value for two items is correct",
                fuzzyEquals(subtotalValueForItemsInCart,manHatPriceProduct * number, TOLERANCE));
    }

    @When("Set quantity for selected by {string} item to index {int}")
    public void setQuantityForSelectedItem(String oldValue, int index) {
        calculatedSubtotal2Items = (manHatPriceProduct * index) + womenHatPriceProduct;
        amazonCartPage.getQuantityByValueAndSetNewOne(oldValue, index, calculatedSubtotal2Items);
        subtotalValueFinal = amazonCartPage.getSubtotalValue();
    }

    @Then("Validate subtotal value is correct after quantity changed")
    public void setQuantityForSelectedItem() {
        assertThat("Subtotal value for two products is correct",
                fuzzyEquals(subtotalValueFinal, calculatedSubtotal2Items, TOLERANCE));
    }

    @Then("Check incorrect input causes absence of item list")
    public void checkForErrorPageText() {
        assertThat("!ncorrect input causes absence of item list",
                amazonSearchPage.getFoundElements().isEmpty());
    }
}