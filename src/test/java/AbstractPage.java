import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

abstract class AbstractPage {

    public static WebDriver driver;
    public static WebDriverWait wait;
    private static final int DEFAULT_WAIT = 60;

    static ExtentTest test;
    static ExtentReports report;
    public static ExtentHtmlReporter htmlReporter;
    public static com.aventstack.extentreports.ExtentReports extent;

    protected void openURL(String url) throws InterruptedException {
        navigateTo(url);
    }

    protected void navigateTo(String url) {
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        System.out.println("Web application launched");
    }

    protected void untilElementAppear(By by) {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // wait.until(ExpectedConditions.presenceOfElementLocated(by));
        // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected WebElement findElement(By by, int... index) throws InterruptedException {
        WebElement element;
        untilElementAppear(by);

        if (index.length == 0)
            element = driver.findElement(by);
        else
            element = driver.findElements(by).get(index[0]);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
        //((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);

        // wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }

    protected List<WebElement> findElements(By by) {
        List<WebElement> webElements;
        untilElementAppear(by);
        webElements = driver.findElements(by);
        return webElements;
    }

    protected void sendKeys(By by, String text) throws InterruptedException {
        WebElement element;
        element = findElement(by);
        if (element.isEnabled()) {
            element.sendKeys(text);
        }
    }

    protected void click(By by, int... index) throws InterruptedException {
        WebElement element;
        element = findElement(by, index);
        element.click();
    }

    protected void control(boolean statement, String onTrue, String onFalse) {
        if (statement == true) {
            System.out.println(onTrue);
        } else {
            System.out.println(onFalse);
            Assert.assertTrue(false);
        }
    }

    protected boolean isElementExist(By by) {
        return isElementExist(by, DEFAULT_WAIT);
    }

    protected boolean isElementExist(By by, int timeSeconds) {
        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        boolean isExist = driver.findElements(by).size() > 0;
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        return isExist;
    }

    public AbstractPage compareText(String SentText, String TextFromScreen) {
        System.out.println("Sent text: " + SentText);
        System.out.println("Text from Screen: " + TextFromScreen);
        control(TextFromScreen.contains(SentText), "Compare Successfull", "Compare is not successfull");
        return this;
    }

    protected String getTextOfElement(By by, int... index) throws InterruptedException {
        WebElement element;
        String text;
        element = findElement(by, index);
        text = element.getText();
        return text;
    }

    protected String getRandomNames(int randomStringSize) {
        String randomSumberString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < randomStringSize) {
            int index = (int) (rnd.nextFloat() * randomSumberString.length());
            salt.append(randomSumberString.charAt(index));
        }
        return salt.toString();
    }

    public String getBrowserName(String BrowserName) {
        if (BrowserName.contains("ie")) {
            BrowserName = "INTERNET EXPLORER";
        } else if (BrowserName.contains("firefox")) {
            BrowserName = "FIREFOX";
        } else if (BrowserName.contains("chrome")) {
            BrowserName = "CHROME";
        } else {
            BrowserName = "HTMLUNIT DRIVER";
        }
        return BrowserName;
    }
}
