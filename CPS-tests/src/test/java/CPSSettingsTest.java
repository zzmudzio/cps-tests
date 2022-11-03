import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.WebDrivers;
import pl.operations.CsvReader;
import pl.pages.CPSAppSettings;
import java.time.Duration;

public class CPSSettingsTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private CPSAppSettings testingObject;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera oraz tworzenie obiektu testowego.");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        testingObject = new CPSAppSettings(driver, driverWait);
    }

    @Test(priority = 0)
    public void testSettingsPageOpeningProperly() {

        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony konfiguracji portalu.");
        testingObject.goToMainPage();
        Assert.assertTrue(testingObject.goToSettings());

    }

    @Test(priority = 1)
    public void testAddConnectionButton() {

        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku \'Dodaj połączenie\'.");
        testingObject.goToMainPage();
        Assert.assertTrue(testingObject.clickAddConnection());

    }

    /* at this moment, there is a possibility for testing just a single db connection */
    @Test(priority = 2)
    public void testAddNewConnection() {

        String[][] dataArray = CsvReader.readDbConnectionData();
        if(dataArray.length < 2) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(testingObject.addDbConnections(dataArray[1][0], dataArray[1][1],
                dataArray[1][2], dataArray[1][3]));
        /* dataArray[1]... because dataArray[0] contains CSV file header */
    }

    @Test(priority = 3)
    public void testValidConnection() {

        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku \'Test połączenia\'.");
        Assert.assertTrue(testingObject.clickTestConnection());
    }

    @AfterTest(enabled = false)
    public void closeAndQuitDriver() {
        System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }
}
