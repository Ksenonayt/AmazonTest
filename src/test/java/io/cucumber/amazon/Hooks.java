package io.cucumber.amazon;

import io.cucumber.amazon.utils.pages.AmazonCartPage;
import io.cucumber.amazon.utils.pages.AmazonProductPage;
import io.cucumber.amazon.utils.pages.AmazonSearchPage;
import io.cucumber.amazon.utils.properties.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static AmazonSearchPage amazonSearchPage;
    public static AmazonProductPage amazonProductPage;
    public static AmazonCartPage amazonCartPage;
    public static WebDriver driver;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("wait.timeout")),
                TimeUnit.SECONDS);
        driver.get(ConfigProperties.getProperty("amazon.page"));
        amazonSearchPage = new AmazonSearchPage(driver);
        amazonProductPage = new AmazonProductPage(driver);
        amazonCartPage = new AmazonCartPage(driver);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
