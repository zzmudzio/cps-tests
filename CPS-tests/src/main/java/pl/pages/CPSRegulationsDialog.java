package pl.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.AppPages;
import java.awt.*;

/*

This window appears after the first installation of CPS or after
regulations changing.

 */
public class CPSRegulationsDialog implements AppPages {

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

    @Override
    public String goToPage() {
        driver.get(AppPages.MAIN_PAGE);
        return driver.getTitle();
    }

    public boolean acceptRegulations() {

        /* Waiting for regulations dialog to be visible */

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogLocator)); //10seconds

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
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogLocator)); //Duration = 10seconds
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(regulationsDialogCancelButtonLocator)); //Duration = 10seconds
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

    @Override
    public void closePage() {
        driver.close();
    }
}
