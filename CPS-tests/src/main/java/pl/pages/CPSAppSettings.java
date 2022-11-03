package pl.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
    By srvNameFieldLocator = new By.ByXPath("//input[@placeholder='Podaj nazwę serwera']");
    By dbNameFieldLocator = new By.ByXPath("//input[@placeholder='Podaj nazwę bazy danych']"); //
    By loginFieldLocator = new By.ByXPath("//input[@placeholder='Podaj login użytkownika SQL']");
    By passwordFieldLocator = new By.ByXPath("//input[@placeholder='Podaj hasło użytkownika SQL']");
    By testConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Test połączenia')]");
    By saveConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Zapisz')]");


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

        Actions addConn = new Actions(driver);
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(srvNameFieldLocator));
            addConn
                    .moveToElement(driver.findElement(srvNameFieldLocator))
                    .click()
                    .sendKeys(serverName)
                    .moveToElement(driver.findElement(dbNameFieldLocator))
                    .click()
                    .sendKeys(dataBaseName)
                    .moveToElement(driver.findElement(loginFieldLocator))
                    .click()
                    .sendKeys(user)
                    .moveToElement(driver.findElement(passwordFieldLocator))
                    .click()
                    .sendKeys(password)
                    .perform();
        }
        catch(Exception e) {
            System.out.println("Wystąpił błąd podczas próby definiowania połączenia do bazy danych.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean clickTestConnection() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(testConnectionButtonLocator));
            driver.findElement(testConnectionButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Przycisk \'Test połączenia\' nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Przycisk \'Test połączenia\' nie został odnaleziony.");
            return false;
        }
        return true;
    }

    public boolean clickSaveConnection() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(saveConnectionButtonLocator));
            driver.findElement(saveConnectionButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Przycisk \'Zapisz połączenie\' nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Przycisk \'Zapisz połączenie\' nie został odnaleziony.");
            return false;
        }
        return true;
    }
}