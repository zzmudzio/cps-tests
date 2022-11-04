package pl.operations;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pl.config.DataFiles;
import java.io.File;
import java.io.IOException;

public class Screenshot {

    public static void takeSnapShot(WebDriver driver, String description) {

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile,
                    new File(DataFiles.SCREENSHOTS_DIR.getScreenshotPath() + description + ".png"));
        }
        catch(IOException ioe) {
            System.out.println("Błąd: nie udało się zapisać zrzutu ekranu o nazwie: " + description);
            ioe.printStackTrace();
        }
    }
}
