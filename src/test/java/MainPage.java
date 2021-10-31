import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends AbstractPage {

    String testcase;

    public MainPage(WebDriver driver, WebDriverWait wait, String testCase) {
        AbstractPage.driver = driver;
        AbstractPage.wait = wait;
        testcase = testCase;
    }

    public MainPage goToUrl(String url) {
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        System.out.println("Web application launched");

        String currentUrl = driver.getCurrentUrl();
        if (driver.getCurrentUrl().contains("euromsgexpress")) {
            System.out.println(currentUrl+": Opened");
            test.log(LogStatus.PASS, "Navigated to the specified URL");
        } else {
            System.out.println(currentUrl+": Could not open");
            test.log(LogStatus.FAIL, "Could not navigate navigate to the specified URL");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage clickGiris() throws InterruptedException {
        if (isElementExist(ObjeRepo.Giris)) {
            click(ObjeRepo.Giris);
            System.out.println(testcase + ": Clicked giris");
            test.log(LogStatus.PASS, "Clicked giris");
        } else {
            test.log(LogStatus.FAIL, "Could not click giris");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage typeEpostaAndPassword(String eposta, String password) throws InterruptedException {
        if (isElementExist(ObjeRepo.LoginPage,15)) {
            control(isElementExist(ObjeRepo.LoginPage), testcase + ": Login page exist", testcase + ": Login page  is not exist");
            sendKeys(ObjeRepo.Eposta, eposta);
            System.out.println(testcase + ": Typed eposta");
            test.log(LogStatus.PASS, "Typed eposta");
        } else {
            test.log(LogStatus.FAIL, "Could not type eposta");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Password)) {
            sendKeys(ObjeRepo.Password, password);
            System.out.println(testcase + ": Typed password");
            test.log(LogStatus.PASS, "Typed password");
        } else {
            test.log(LogStatus.FAIL, "Could not typed password");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.OturumAc)) {
            click(ObjeRepo.OturumAc);
            System.out.println(testcase + ": Clicked oturum ac");
            test.log(LogStatus.PASS, "Clicked oturum ac");
            if (isElementExist(ObjeRepo.Logo,5)) {
                control(isElementExist(ObjeRepo.Logo,5),"Logged successful","Login failed");
                test.log(LogStatus.PASS, "Login successful");
            } else {
                test.log(LogStatus.FAIL, "Login unsuccessful");
                Assert.assertTrue(false);
            }
        } else {
            test.log(LogStatus.FAIL, "Could not click oturum ac");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage clickUyeleriniEkle() throws InterruptedException {
        if (isElementExist(ObjeRepo.UyeleriniEkle)) {
            wait.until(ExpectedConditions.elementToBeClickable(ObjeRepo.UyeleriniEkle));
            click(ObjeRepo.UyeleriniEkle);
            System.out.println(testcase + ": Clicked uyelerini ekle");
            test.log(LogStatus.PASS, "Clicked uyelerini ekle");
        } else {
            test.log(LogStatus.FAIL, "Could not click uyelerini ekle");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage clickYeniListeOlustur() throws InterruptedException {
        if (isElementExist(ObjeRepo.YeniListeOlustur)) {
            wait.until(ExpectedConditions.elementToBeClickable(ObjeRepo.YeniListeOlustur));
            click(ObjeRepo.YeniListeOlustur);
            System.out.println(testcase + ": Clicked yeni liste olustur");
            test.log(LogStatus.PASS, "Clicked yeni liste olustur");
        } else {
            test.log(LogStatus.FAIL, "Could not click yeni liste olustur");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage typeListnameAndClickKaydet(String randomIsim) throws InterruptedException {
        if (isElementExist(ObjeRepo.ListeIsmi)) {
            sendKeys(ObjeRepo.ListeIsmi, randomIsim);
            System.out.println(testcase + ": Typed isim");
            test.log(LogStatus.PASS, "Typed isim");
        } else {
            test.log(LogStatus.FAIL, "Could not type isim");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Kaydet)) {
            click(ObjeRepo.Kaydet);
            System.out.println(testcase + ": Clicked kaydet");
            test.log(LogStatus.PASS, "Clicked kaydet");
        } else {
            test.log(LogStatus.FAIL, "Could not click kaydet");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage selectFormEkleFromDropDown(String formEkle) throws InterruptedException {
        if (isElementExist(ObjeRepo.UyeEkle)) {
            click(ObjeRepo.UyeEkle);
            System.out.println(testcase + ": Clicked Uye ekleye");
            test.log(LogStatus.PASS, "Clicked Uye ekleye");
        } else {
            test.log(LogStatus.FAIL, "Could not click uye ekleye");
            Assert.assertTrue(false);
        }

        Boolean durum = false;
        List<WebElement> options = findElements(ObjeRepo.FormEkleDropDown);
        for (int i = 0; i < options.size(); i++) {
            String text = options.get(i).getText();
            if (text.contains(formEkle)) {
                click(ObjeRepo.FormEkleDropDown);
                durum = true;
                break;
            }
        }

        if (durum == true) {
            System.out.println(testcase + ": Selected form ile ekleme");
            test.log(LogStatus.PASS, "Selected form ile ekleme");
        } else {
            test.log(LogStatus.FAIL, "Could not select form ile Ekleme");
            Assert.assertTrue(false, testcase + ": Could not select form ile ekleme");
        }
        return this;
    }

    public MainPage typeUsernamePasswordAndEposta(String isim, String soyisim, String email) throws InterruptedException {
        if (isElementExist(ObjeRepo.Isim)) {
            sendKeys(ObjeRepo.Isim, isim);
            System.out.println(testcase + ": Typed isim");
            test.log(LogStatus.PASS, "Typed isim");
        } else {
            test.log(LogStatus.FAIL, "Could not type isim");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Soyisim)) {
            sendKeys(ObjeRepo.Soyisim, soyisim);
            System.out.println(testcase + ": Typed soyisim");
            test.log(LogStatus.PASS, "Typed soyisim");
        } else {
            test.log(LogStatus.FAIL, "Could not type soyisim");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Email)) {
            sendKeys(ObjeRepo.Email, email);
            System.out.println(testcase + ": Typed email");
            test.log(LogStatus.PASS, "Typed email");
        } else {
            test.log(LogStatus.FAIL, "Could not type email");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Agreement)) {
            click(ObjeRepo.Agreement);
            System.out.println(testcase + ": Clicked agreement");
            test.log(LogStatus.PASS, "Clicked agreement");
        } else {
            test.log(LogStatus.FAIL, "Could not click agreement");
            Assert.assertTrue(false);
        }

        if (isElementExist(ObjeRepo.Kaydet2)) {
            click(ObjeRepo.Kaydet2);
            System.out.println(testcase + ": Clicked kaydet");
            test.log(LogStatus.PASS, "Clicked kaydet");
        } else {
            test.log(LogStatus.FAIL, "Could not click kaydet");
            Assert.assertTrue(false);
        }
        return this;
    }

    public MainPage compareMessage(String uye, String basarili) throws InterruptedException {
        String basariliText = getTextOfElement(ObjeRepo.BasariliMessage);
        String uyeBasariliText = getTextOfElement(ObjeRepo.BasariliMessage2);

        if(basariliText.length()>0){
            compareText(uye, uyeBasariliText);
            test.log(LogStatus.PASS, "Compare Succesfull");
        }else{
            test.log(LogStatus.FAIL, "Could not compare text");
            Assert.assertTrue(false);
        }

        if(uyeBasariliText.length()>0){
            compareText(basarili, basariliText);
            test.log(LogStatus.PASS, "Compare Succesfull");
        }else{
            test.log(LogStatus.FAIL, "Could not compare text");
            Assert.assertTrue(false);
        }
        return this;
    }

}
