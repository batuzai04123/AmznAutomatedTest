package com.nat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nat.constants.Urls.AMAZON_HOME_URL;

/**
 * Amazon Home Page Object
 */
public class AmazonHomePage extends BasePage {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchTextBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "searchDropdownBox")
    private WebElement categoryDropdown;

    @FindBy(id = "low-price")
    private WebElement minPriceTextBox;

    @FindBy(id = "high-price")
    private WebElement maxPriceTextBox;

    @FindBy(css = "#a-autoid-1 input")
    private WebElement priceSetButton;

    @FindAll({
        @FindBy(css = ".s-main-slot > .s-asin")
    })
    private WebElement searchResults;

    public AmazonHomePage open() {
        driver.get(AMAZON_HOME_URL);
        return this;
    }

    public AmazonHomePage searchContent(String content) {
        waitForVisibility(searchTextBox).sendKeys(content);
        return this;
    }

    public AmazonHomePage clickSearch() {
        searchButton.click();
        return this;
    }

    public AmazonHomePage selectCategory(String category) {

        WebElement el = driver.findElement(By.xpath("//div[@id='departments']//a/span[text()='Cell Phones']"));
        waitForClick(el);
        return this;
    }

    public AmazonHomePage setPriceRange(String minPrice, String maxPrice) {
        setText(minPriceTextBox, minPrice);
        setText(maxPriceTextBox, maxPrice);
        waitForClick(priceSetButton);
        return this;
    }

    public AmazonHomePage getTopFiveResultsAndPrintOutput()
    {
        // .s-main-slot > .s-asin h2.a-size-mini
        List<WebElement> results = driver.findElements(By.cssSelector(".s-main-slot > .s-asin"));

        // Get top 5 Elements only
        results = results.stream().limit(5).toList();

        Map<String, String> map = new LinkedHashMap<>();

        // Extract Product Name and Price and save it to a LinkedHashMap
        for (WebElement result : results) {
            String title = result.findElement(By.cssSelector("h2.a-size-mini")).getText();
            String price = result.findElement(By.cssSelector(".a-price:not(.a-text-price)"))
                    .getText().trim().replaceAll("\n", ".");
            map.put(title, price);
        }

        // Reversed Map
        LinkedHashMap<String, String> reverseSortedMap = new LinkedHashMap<>();

        // Reverse Map by value(Price) in Descending order
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Reverse order
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue())); // save each record to Reversed Map

        // Printout the Key-Values
        reverseSortedMap.entrySet().stream().forEach(System.out::println);
        return this;
    }
}
