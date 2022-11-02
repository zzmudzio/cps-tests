import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.AppPages;
import pl.config.WebDrivers;
import pl.pages.CPSRegulationsDialog;
import java.time.Duration;


public class CPSRegulationsDialogTest {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera. ");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 0)
    public void testMainPageOpeningProperly() {

        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony głównej portalu.");
        /* Test passes if the title of the opened page is equal to given as assertion parameter */
        CPSRegulationsDialog pageTitle = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertEquals(pageTitle.goToPage(), "Currenda Portal Statystyczny");
    }

    @Test(priority = 1)
    public void testClickingCancelButton() {

        System.out.println("[Test] Weryfikacja pojawienia się komunikatu podczas próby " +
                "zamknięcia regulaminu bez jego akceptacji.");
        CPSRegulationsDialog cancelButton = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertTrue(cancelButton.cancelRegulations());

    }
    @Test(priority = 2)
    public void testClosingNonAcceptedDialog() {

        System.out.println("[Test] Weryfikacja skuteczności zamknięcia okna dialogowego informującego o" +
                "konieczności akceptacji regulaminu.");
        CPSRegulationsDialog cancelButton = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertTrue(cancelButton.closeRegulationsWarningWindow());

    }
    @Test(priority = 3)
    public void testClickingAcceptButton() {

        System.out.println("[Test] Weryfikacja skuteczności akceptacji regulaminu portalu. ");
        CPSRegulationsDialog dialogCloseButton = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertTrue(dialogCloseButton.acceptRegulations());

    }

   @AfterTest
    public void closeAndQuitDriver() {
       System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }
}
