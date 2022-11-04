import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.DataFiles;
import pl.config.WebDrivers;
import pl.operations.Screenshot;
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
    public void testMainPageOpeningProperly() {
        System.out.println("[Test] Weryfikacja skuteczności otwarcia strony głównej aplikacji.");
        mainWindowHandle = testingObject.goToMainPage();
        Assert.assertEquals(driver.getTitle(), "Currenda Portal Statystyczny");
    }

    @Test(priority = 1)
    public void testOpeningReportsInstruction() throws InterruptedException {
        System.out.println("[Test] Weryfikacja skuteczności otwarcia instrukcji raportów.");
        String newWindowHandle = testingObject.goToReportsInstruction();
        System.out.println("[Test] Nastąpi porównanie identyfikatorów okien - porównanie id okna głównego" +
                " z id nowo otwartego okna raportów(muszą być różne)");
        Assert.assertNotEquals(mainWindowHandle, newWindowHandle);
        driver.switchTo().window(newWindowHandle);
        Thread.sleep(3000); //to slow down a little bit
        System.out.println("Wykonywanie zrzutu ekranu, dostępny w lokalizacji: "
                + DataFiles.SCREENSHOTS_DIR.getScreenshotPath());
        Screenshot.takeSnapShot(driver, "Instrukcje-Raportow");
        System.out.println("[Test] Następuje zamknięcie nowego okna oraz przejście do głównej strony.");
        WebDrivers.closeWindow(driver);
        driver.switchTo().window(mainWindowHandle);
    }
}
