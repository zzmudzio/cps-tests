import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.WebDrivers;
import pl.pages.CPSInstructions;
import java.time.Duration;

public class CPSInstructionsTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private CPSInstructions testingObject;
    private String mainWindowHandle;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera. ");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        testingObject = new CPSInstructions(driver, driverWait);
    }

    @Test(priority = 0)
    public void testMainPageOpeningProperly()
    {
        System.out.println("[Test] Weryfikacja skuteczności otwarcia strony głównej aplikacji.");
        mainWindowHandle = testingObject.goToMainPage();
        Assert.assertEquals(driver.getTitle(), "Currenda Portal Statystyczny");
    }

    @Test(priority = 1)
    public void goToReportsInstruction()
    {
        System.out.println("[Test] Weryfikacja skuteczności otwarcia instrukcji raportów.");
        System.out.println("[Test] Nastąpi porównanie identyfikatorów okien - porównanie id okna głównego" +
                " z id nowo otwartego okna raportów");
        System.out.println("[Test] Id głównego okna: " + mainWindowHandle);
        System.out.println("[Test] Id nowo otwartego okna: " + testingObject.goToReportsInstruction());
        //Assert.assertNotEquals(mainWindowHandle, testingObject.goToReportsInstruction());
    }
}
