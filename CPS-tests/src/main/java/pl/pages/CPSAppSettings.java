package pl.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.AppPages;

public class CPSAppSettings extends AppPages{

    private WebDriver driver;
    private WebDriverWait driverWait;

    public CPSAppSettings(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    By settingsButtonLocator = new By.ByXPath("//div[@data-qa='sidebar_Konfiguracja']");

    public String goToMainPage() {
        driver.get(AppPages.getMainPageAddress());
        return driver.getTitle();
    }

    public boolean goToSettings() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(settingsButtonLocator));
            driver.findElement(settingsButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Przycisk konfiguracji nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Przycisk konfiguracji nie został odnaleziony.");
            return false;
        }
        return true;
    }
}
