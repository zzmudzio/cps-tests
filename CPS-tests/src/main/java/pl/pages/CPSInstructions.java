package pl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.AppPages;
import java.util.ArrayList;
import java.util.List;

public class CPSInstructions {

    private final WebDriver driver;
    private final WebDriverWait driverWait;

    public CPSInstructions(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }
    private final By instructionsMainButtonLocator = new By.ByXPath("//div[contains(text(), 'Instrukcje')]");
    private final By instructionsReportsAndStatisticsLocator = new By.ByXPath("//*[@data-qa='sidebar_1. " +
            "Definicje statystyk i raportów']");

    public String goToMainPage() {
        driver.get(AppPages.getMainPageAddress());
        return driver.getWindowHandle(); // we need the main window handle because of further tests
    }

    public String goToReportsInstruction() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(instructionsMainButtonLocator));
            driver.findElement(instructionsMainButtonLocator).click();
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(instructionsReportsAndStatisticsLocator));
            driver.findElement(instructionsReportsAndStatisticsLocator).click();
            List<String> windowsHandleList = new ArrayList<>(driver.getWindowHandles()); //because Set doesn't provide get method
            return windowsHandleList.get(1); //because at position '0' is being stored main window id
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: przycisk instrukcji nie pojawił się.");
            return null;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: przycisk instrukcji nie został odnaleziony");
            return null;
        }
    }
}
