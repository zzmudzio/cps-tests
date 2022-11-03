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
    By addConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Dodaj połączenie')]");
    By srvNameFieldLocator = new By.ByXPath("//input[@id='textbox_40']");
    By dbNameFieldLocator = new By.ByXPath("//input[@id='textbox_41']");
    By loginFieldLocator = new By.ByXPath("//input[@id='textbox_42']");
    By passwordFieldLocator = new By.ByXPath("//input[@id='textbox_43']");


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

    public boolean clickAddConnection() {

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(addConnectionButtonLocator));
            driver.findElement(addConnectionButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Przycisk dodawania połączenia nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Przycisk dodawania połączenia nie został odnaleziony");
            return false;
        }
        return true;
    }

    public boolean addDbConnections(String serverName, String dataBaseName, String user, String password) {

        return false;

    }
}
