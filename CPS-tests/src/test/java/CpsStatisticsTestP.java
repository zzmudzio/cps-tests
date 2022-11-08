import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.pages.CPSStatistics;


public class CpsStatisticsTestP extends CPSTestsMain {
    private CPSStatistics testingObject;
    @BeforeTest
    public void initializeDriver() {
        super.initializeDriver();
        testingObject = new CPSStatistics(driver, driverWait);
    }

    @Test(priority=0)
    public void testStatisticsPageOpeningProperly() {
        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony statystyk spraw.");
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

    @AfterTest
    public void closeAndQuitDriver() {
        super.closeAndQuitDriver();
    }
}
