import org.openqa.selenium.By;

public class ObjeRepo {
    public static final By Giris = By.xpath("//div[@id='page-header']/div/div[2]/div[1]");
    public static final By Eposta = By.id("exampleInputEmail1");
    public static final By Password = By.id("exampleInputPassword1");
    public static final By OturumAc = By.cssSelector("[name='loginForm'] button");
    public static final By LoginPage = By.cssSelector("div[class='card-header text-center']");
    public static final By UyeleriniEkle = By.xpath("(//div[@fxlayoutalign='start center'])[1]//div[3]//h4[2]");
    public static final By YeniListeOlustur = By.cssSelector("button[class='btn btn-labeled btn-purple']");
    public static final By ListeIsmi = By.cssSelector("input[placeholder='Listeye isim ver']");
    public static final By Kaydet = By.cssSelector("[class='btn btn-labeled btn-success ng-star-inserted']");
    public static final By UyeEkle = By.cssSelector("div[class='card card-default'] div[class='row'] button");
    public static final By FormEkleDropDown = By.cssSelector("a[class='dropdown-item']");
    public static final By Isim = By.id("firstName");
    public static final By Soyisim = By.id("lastName");
    public static final By Email = By.id("email");
    public static final By Kaydet2 = By.cssSelector("button[class='btn btn-labeled btn-success']");
    public static final By Agreement = By.cssSelector("span[class='fa fa-check']");
    public static final By BasariliMessage = By.cssSelector("[class='toast-title']");
    public static final By BasariliMessage2 = By.cssSelector("[class='toast-message']");
    public static final By Logo = By.cssSelector("div[class='brand-logo']");


}
