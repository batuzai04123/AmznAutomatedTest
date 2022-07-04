package com.nat.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public void init(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForClick(WebElement element) {
        WebElement customElement = customWait().until(ExpectedConditions.elementToBeClickable(element));
        customElement.click();
    }

    public WebElement waitForVisibility(WebElement element) {
        return customWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void setText(WebElement element, String text) {
        customWait().until(ExpectedConditions.visibilityOf(element))
                .sendKeys(text);
    }

    protected WebDriverWait customWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

}
