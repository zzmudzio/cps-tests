import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.pages.CPSRegulationsDialog;


public class CpsRegulationsDialogTestP extends CPSTestsMain {
    private CPSRegulationsDialog testingObject;

    @BeforeTest
    public void initializeDriver() {
        super.initializeDriver();
        testingObject = new CPSRegulationsDialog(driver, driverWait);
    }

    @Test(priority = 0)
    public void testMainPageOpeningProperly() {
        System.out.println("[Test] Weryfikacja poprawnego otwarcia strony głównej portalu.");
        /* Test passes if the title of the opened page is equal to given as assertion parameter */
        Assert.assertEquals(testingObject.openMainPage(), "Currenda Portal Statystyczny");
    }

    @Test(priority = 1)
    public void testClickingCancelButton() {
        System.out.println("[Test] Weryfikacja pojawienia się komunikatu podczas próby " +
                "zamknięcia regulaminu bez jego akceptacji.");
        Assert.assertTrue(testingObject.cancelRegulations());
    }
    @Test(priority = 2)
    public void testClosingNonAcceptedDialog() {
        System.out.println("[Test] Weryfikacja skuteczności zamknięcia okna dialogowego informującego o" +
                "konieczności akceptacji regulaminu.");
        Assert.assertTrue(testingObject.closeRegulationsWarningWindow());
    }
    @Test(priority = 3)
    public void testClickingAcceptButton() {
        System.out.println("[Test] Weryfikacja skuteczności akceptacji regulaminu portalu. ");
        Assert.assertTrue(testingObject.acceptRegulations());
    }

   @AfterTest
    public void closeAndQuitDriver() {
           super.closeAndQuitDriver();
    }
}
