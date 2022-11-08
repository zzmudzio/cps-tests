import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.config.WebDrivers;
import pl.pages.CPSStatistics;
import java.time.Duration;

public class CPSStatisticsTest_P {
    private WebDriver driver;
    private WebDriverWait driverWait;
    private CPSStatistics testingObject;

    @BeforeTest
    public void initializeDriver() {
        System.out.println("[Test] Trwa inicjalizowanie drivera oraz tworzenie obiektu testowego.");
        driver = WebDrivers.initializeChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        testingObject = new CPSStatistics(driver, driverWait);
    }

    @Test(priority=0)
    public void testStatisticsPageOpeningProperly() {
        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony statystyk spraw.");
        testingObject.goToMainPage();
        Assert.assertEquals(testingObject.goToStatisticsPage(), "Statystyki pomocnicze / Statystyka spraw");
    }

    @Test(priority=1)
    public void testChoosingCorrectStatType() {
        System.out.println("[Test] Weryfikacja wyboru prawidłowego rodzaju statystyk (w tym wypadku: Ruch spraw)");
        Assert.assertTrue(testingObject.chooseStatisticsType());
    }

    @Test(priority=2)
    public void testStatisticsCalculations() {
        System.out.println("[Test] Weryfikacja prawidłowości obliczeń statystycznych - suma powinna być" +
                "różna od '0'");
        Assert.assertNotEquals(testingObject.getStatisticsCalculations(), "0"); // if the value is different from
        // 0, we can assume that the calculations are correct, it is of course a trivial test-case, but allows to verify
        // the correctness of system implementation in general.
    }

    @AfterTest(enabled = false)
    public void closeAndQuitDriver() {
        System.out.println("Kończenie pracy drivera.");
        driver.quit();
    }
}
