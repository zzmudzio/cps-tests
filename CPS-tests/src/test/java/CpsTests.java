import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.WebDrivers;
import java.time.Duration;

public class CpsTests {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    public void initializeDriver() {
        this.driver = WebDrivers.initializeChromeDriver();
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
