package com.nat.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Driver Manager
 */
public class DriverManager {

    private static final int TIME_0UT = 30;
    private static WebDriver driver;

    private DriverManager() {}

    /**
     * Initialize Webdriver
     * @return driver   instance of webdriver
     */
    private static WebDriver init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-site-isolation-trials");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_0UT));
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriver() {
        return (driver == null) ? init() : driver;
    }
}
