import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.WebDrivers;
import pl.pages.CPSAppSettings;
import pl.pages.CPSRegulationsDialog;

import java.time.Duration;

public class CPSSettingsTest {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera. ");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 0)
    public void testSettingsPageOpeningProperly() {

        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony konfiguracji portalu.");
        CPSAppSettings settingsPage = new CPSAppSettings(driver, driverWait);
        settingsPage.goToMainPage();
        Assert.assertTrue(settingsPage.goToSettings());

    }

    @AfterTest(enabled = false)
    public void closeAndQuitDriver() {
        System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }

}
