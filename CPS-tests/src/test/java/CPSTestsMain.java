import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.WebDrivers;
import java.time.Duration;

public class CPSTestsMain {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera oraz tworzenie obiektu testowego.");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void closeAndQuitDriver() {
        System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }
}
