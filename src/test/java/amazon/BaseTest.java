package amazon;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nat.driver.DriverManager;
import com.nat.pages.AmazonHomePage;
import com.nat.pages.BasePage;
import com.nat.pages.BasePageFactory;
import com.nat.report.ExtentReportManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;
    protected AmazonHomePage amazonHomePage;
    protected WebDriver driver = DriverManager.getDriver();

    public abstract void init();

    protected <T extends BasePage> T createInstance(Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }

    public void openAmazonPage() {
        amazonHomePage.open();
    }

    @BeforeClass
    public void setup() {
        Logger.getRootLogger().setLevel(Level.OFF);
        init();

        extentReports = ExtentReportManager.createReport();
        amazonHomePage = createInstance(AmazonHomePage.class);
    }

    @BeforeMethod
    public void register(Method method) {
        String testName = method.getName();
        extentTest = extentReports.createTest(testName);
    }

    @AfterMethod
    public void assessAfterTest(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "TEST FAILED: " + result.getName());
            extentTest.log(Status.FAIL, "TEST FAILED: " + result.getThrowable());
        } else if(result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.PASS, "TEST SKIPPED: " + result.getName());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "TEST PASSED: " + result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        extentReports.flush();
        driver.quit();
    }
}
