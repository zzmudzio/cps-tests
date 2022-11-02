package pl.config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDrivers {
    private static final String CHROMEDRIVER_PATH = "C:\\Users\\m.zmuda-trzebia\\Desktop\\Java\\ChromeDriver" +
            "\\chromedriver.exe";
    private static WebDriver driver;
    public static ChromeDriver initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        return new ChromeDriver();
    }
    public static String getChromeDriverPath() {
        return CHROMEDRIVER_PATH;
    }
}
