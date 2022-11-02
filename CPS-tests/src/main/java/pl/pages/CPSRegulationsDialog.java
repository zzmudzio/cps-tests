package pl.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.AppPages;
import java.awt.*;

/*

This window appears after the first installation of CPS or after
regulations changing.

 */
public class CPSRegulationsDialog extends AppPages {

    WebDriver driver;
    WebDriverWait driverWait;

    public CPSRegulationsDialog(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    By regulationsDialogLocator = new By.ByXPath("/html/body/div/div[1]");
    By regulationsDialogCancelButtonLocator = new By.ByXPath("//button[contains(text(), \"Anuluj\")]");
    By regulationsDialogAcceptButtonLocator = new By.ByXPath(".//div[@class='e-dlg-container " +
            "e-dlg-center-center']" +
                    "/div/div[3]/span/button[contains(text(), 'Akceptuję')]");
    By regulationsNotAcceptedCloseButtonLocator = new By.ByXPath("//button[contains(text(), 'Zamknij')]");

    @Override
    public String goToMainPage() {
        driver.get(AppPages.getMainPageAddress());
        return driver.getTitle();
    }

    public boolean acceptRegulations() {

        /* Waiting for regulations dialog to be visible */

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogLocator)); //5 seconds
            /* Regulation dialog has to be scrolled down for accept button to be enabled */
            Robot mouse = new Robot();
            mouse.mouseWheel(300);
        }
        catch(TimeoutException te) {
            System.out.println("Okno regulaminu nie pojawiło się. \n");
            return false;
        }
        catch(AWTException awte) {
            System.out.println("Nie udało się przescrollować regulaminu");
        }

        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(regulationsDialogAcceptButtonLocator));
            driver.findElement(regulationsDialogAcceptButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Przycisk \"Akceptuj\" nie pojawił się. ");
            return false;
        }
        return true;
    }

    public boolean cancelRegulations() {

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogLocator)); //Duration = 5s
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogCancelButtonLocator)); //Duration = 5s
            driver.findElement(regulationsDialogCancelButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Okno regulaminu nie pojawiło się. \n");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Nie udało się odnaleźć przycisku Cancel.");
            return false;
        }
        return true;
    }

    public boolean closeRegulationsWarningWindow() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsNotAcceptedCloseButtonLocator)); //Duration = 5s
            driver.findElement(regulationsNotAcceptedCloseButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Okno informacyjne dot. konieczności akceptacji regulaminu nie pojawiło się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Przycisk \'Zamknij\' nie pojawił się. ");
            return false;
        }
        return true;
    }
}
