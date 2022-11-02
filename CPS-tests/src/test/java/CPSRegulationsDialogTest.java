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

        System.out.println("[Test] czy otwiera się strona główna.");
        /* Test passes if the title of the opened page is equal to given as assertion parameter */
        CPSRegulationsDialog pageTitle = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertEquals(pageTitle.goToPage(), "Currenda Portal Statystyczny");
        System.out.println("Strona główna otworzyła się poprawnie.");
    }

    @Test(priority = 1)
    public void testClickingAcceptButton() {

        System.out.println("[Test] próba akceptacji okna regulaminu.");
        CPSRegulationsDialog acceptButton = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertTrue(acceptButton.acceptRegulations());

    }

    @Test(priority = 2)
    public void testClickingCancelButton() {

        System.out.println("[Test] próba anulowania okna regulaminu.");
        CPSRegulationsDialog cancelButton = new CPSRegulationsDialog(driver, driverWait);
        Assert.assertTrue(cancelButton.cancelRegulations());

    }

   /* @AfterTest
    public void closeAndQuitDriver() {
        driver.quit();
    } */
}
