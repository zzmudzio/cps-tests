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

    private final WebDriver driver;
    private final WebDriverWait driverWait;

    public CPSAppSettings(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    private final By settingsButtonLocator = new By.ByXPath("//div[@data-qa='sidebar_Konfiguracja']");
    private final By addConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Dodaj połączenie')]");
    private final By srvNameFieldLocator = new By.ByXPath("//input[@placeholder='Podaj nazwę serwera']");
    private final By dbNameFieldLocator = new By.ByXPath("//input[@placeholder='Podaj nazwę bazy danych']"); //
    private final By loginFieldLocator = new By.ByXPath("//input[@placeholder='Podaj login użytkownika SQL']");
    private final By passwordFieldLocator = new By.ByXPath("//input[@placeholder='Podaj hasło użytkownika SQL']");
    private final By testConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Test połączenia')]");
    private final By saveConnectionButtonLocator = new By.ByXPath("//button[contains(text(), 'Zapisz')]");
    private final By closeConnectionWindowButtonLocator = new By.ByXPath("//button[@title='Zamknij']");
    private final By dbConnectionEntryLocator = new By.ByXPath("//td[@aria-label='1 column header Lp.' and contains(text(), '1')]");

    public String goToMainPage() {
        return super.goToMainPage(driver);
    }

    public boolean goToSettings() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(settingsButtonLocator));
            driver.findElement(settingsButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: Przycisk konfiguracji nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Przycisk konfiguracji nie został odnaleziony.");
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
            System.out.println("Błąd: Przycisk dodawania połączenia nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Przycisk dodawania połączenia nie został odnaleziony");
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
            System.out.println("Błąd: Przycisk 'Test połączenia' nie pojawił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Przycisk 'Test połączenia' nie został odnaleziony.");
            return false;
        }
        return true;
    }

    public boolean clickSaveConnection() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(saveConnectionButtonLocator));
            driver.findElement(saveConnectionButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: Przycisk 'Zapisz połączenie' nie uaktywnił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Przycisk 'Zapisz połączenie' nie został odnaleziony.");
            return false;
        }
        return true;
    }

    public boolean clickCloseConnectionWindow() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(closeConnectionWindowButtonLocator));
            driver.findElement(closeConnectionWindowButtonLocator).click();
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: Przycisk 'Zamknij' nie uaktywnił się.");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Przycisk 'Zamknij' nie został odnaleziony.");
            return false;
        }
        return true;
    }
    public boolean verifyConnectionExistence() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(dbConnectionEntryLocator));
            driver.findElement(dbConnectionEntryLocator);
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: połączenie do bazy danych nie zostało poprawnie dodane. " +
                    "Nie odnaleziono rekordu.");
            return false;
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: połączenie do bazy danych nie zostało odnalezione.");
            return false;
        }
        return true;
    }
}
