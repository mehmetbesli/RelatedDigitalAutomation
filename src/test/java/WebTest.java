import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    public void BeforeTest() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 15);

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
