import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WebTest extends AbstractPage {


    @BeforeClass
    public static void startTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String sysDate = dtf.format(now);

        report = new ExtentReports(System.getProperty("user.dir")+"\\TestReport\\"+sysDate+".html");
        //Case Name
        test = report.startTest("Add Member");
    }


    @BeforeTest
    public void BeforeTest() throws Exception {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\lib\\chromedriver.exe");

        File f = new File("lib");
        File chromeDriver = new File(f,"chromedriver.exe");
        File firefoxDriver = new File(f,"geckodriver.exe");
        File ieDriver = new File(f,"IEDriverServer.exe");

        String browserName=getBrowserName("ie").toLowerCase();

        if (browserName.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--disable-notifications");
            ops.addArguments("--disable-popup-blocking");
            ops.addArguments("--disable-infobars");
            ops.addArguments("--start-maximized");

            driver = new ChromeDriver(ops);
            wait = new WebDriverWait(driver, 15);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());
            System.setProperty("webdriver.firefox.bin", "C:\\Users\\user\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
            FirefoxOptions fos = new FirefoxOptions();
            DesiredCapabilities capabilityF = DesiredCapabilities.firefox();
            capabilityF.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fos);
            capabilityF.setBrowserName("firefox");
            capabilityF.setJavascriptEnabled(true);

            driver = new FirefoxDriver(fos);
            wait = new WebDriverWait(driver, 15);

        } else if (browserName.equalsIgnoreCase("internet explorer")) {
            System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            //options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

            driver = new InternetExplorerDriver(options);
            wait = new WebDriverWait(driver, 15);
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void Test() throws InterruptedException {
        String className = this.getClass().getSimpleName();
        String testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        String testCase =className + "." + testCaseName;

        MainPage mainPage = new MainPage(driver, wait, testCase);

        String randomIsim=getRandomNames(10);

        mainPage
                .goToUrl(Data.Url)
                .clickGiris()
                .typeEpostaAndPassword(Data.Eposta, Data.Password)
                .clickUyeleriniEkle()
                .clickYeniListeOlustur()
                .typeListnameAndClickKaydet(randomIsim)
                .selectFormEkleFromDropDown(Data.FormEkle)
                .typeUsernamePasswordAndEposta(Data.Isim, Data.Soyisim, Data.Eposta)
                .compareMessage(Data.Uye, Data.Basarili);
    }

    @AfterClass
    public static void endTest() {
        report.flush();
    }

    @AfterTest
    public void AfterTest(){
        driver.quit();
    }

}
