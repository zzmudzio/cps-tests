import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.operations.CsvReader;
import pl.pages.CpsAppSettings;

public class CpsSettingsTestPositive extends CpsTests {
    private CpsAppSettings testingObject;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera oraz tworzenie obiektu testowego.");
        super.initializeDriver();
        testingObject = new CpsAppSettings(driver, driverWait);
    }

    @Test(priority = 0)
    public void testSettingsPageOpeningProperly() {
        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony konfiguracji portalu.");
        testingObject.goToMainPage();
        Assert.assertTrue(testingObject.goToSettings());
    }

    @Test(priority = 1)
    public void testAddConnectionButton() {
        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku 'Dodaj połączenie'.");
        Assert.assertTrue(testingObject.clickAddConnection());
    }

    /* at this moment, there is a possibility for testing just a single db connection */
    @Test(priority = 2)
    public void testAddNewConnection() {
        String[][] dataArray = CsvReader.readDbConnectionData();
        if(dataArray.length < 2) {
            Assert.fail();
        }
        Assert.assertTrue(testingObject.addDbConnections(dataArray[1][0], dataArray[1][1],
                dataArray[1][2], dataArray[1][3]));
        /* dataArray[1]... because dataArray[0] contains CSV file header */
    }

    @Test(priority = 3)
    public void testValidConnection() {
        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku 'Test połączenia'.");
        Assert.assertTrue(testingObject.clickTestConnection());
    }

    @Test(priority = 4)
    public void testSaveConnection() {
        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku 'Zapisz'.");
        Assert.assertTrue(testingObject.clickSaveConnection());
    }

    @Test(priority = 5)
    public void testCloseConnectionWindow() {
        System.out.println("[Test] Weryfikacja poprawnej pracy przycisku 'Zamknij'.");
        Assert.assertTrue(testingObject.clickCloseConnectionWindow());
    }

    @Test(priority = 6)
    public void testDbConnectionRecordVisibility() {
        System.out.println("[Test] Weryfikacja istnienia zdefiniowanego połączenia do bazy danych.");
        Assert.assertTrue(testingObject.verifyConnectionExistence());
    }

    @AfterTest
    public void closeAndQuitDriver() {
        System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }
}
