package pl.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.config.AppPages;


public class CPSStatistics {
    private WebDriver driver;
    private WebDriverWait driverWait;

    public CPSStatistics(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    private final By caseStatisticsButtonLocator = new By.ByXPath("//p[contains(text(), " +
            "'Statystyka spraw')]");
    private final By caseStatisticsPageHeaderLocator = new By.ByXPath("//div[contains(text(), " +
            "' Statystyki pomocnicze / Statystyka spraw ')]");

    private final By statisticsTypeLocator = new By.ByXPath("//input[@placeholder" +
            "=\"-- Wybierz rodzaj statystyki --\"]");

    private final By casesComeInSum = new By.ByXPath("//td[@class='dx-area-data-cell']" +
            "//tbody/tr[13]/td[1]/span");

    public String goToMainPage() {
        driver.get(AppPages.getMainPageAddress());
        driver.manage().window().maximize();
        return driver.getTitle();
    }

    public String goToStatisticsPage() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(caseStatisticsButtonLocator));
            driver.findElement(caseStatisticsButtonLocator).click();
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(caseStatisticsPageHeaderLocator));
            return driver.findElement(caseStatisticsPageHeaderLocator).getText();
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: przycisk uruchamiania statystyk nie pojawił się.");
            return null;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: przycisk uruchamiania statystyk nie został odnaleziony.");
            return null;
        }
    }

    public boolean chooseStatisticsType() {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(statisticsTypeLocator));
            Actions statisticsChoser = new Actions(driver);
            statisticsChoser
                    .moveToElement(driver.findElement(statisticsTypeLocator))
                    .click()
                    .keyDown(Keys.ARROW_DOWN)
                    .keyUp(Keys.ARROW_UP)
                    .build()
                    .perform();
            return true;
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: Lista z typami statystyk nie pojawiła się");
            return false;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: lista z typami statystyk nie została odnaleziona.");
            return false;
        }
    }

    public String getStatisticsCalculations() {
        String totalSumAsStr = null;
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(casesComeInSum));
            return driver.findElement(casesComeInSum).getText();
        }
        catch(TimeoutException te) {
            System.out.println("Błąd: Suma wpływu spraw nie pojawiła się. ");
            return null;
        }
        catch(NotFoundException nfe) {
            System.out.println("Błąd: Obiekt suma wpływu spraw nie został odnaleziony.");
            return null;
        }
    }
}
