package pl.config;


import org.openqa.selenium.WebDriver;

public class AppPages {
        private static final String MAIN_PAGE_ADDRESS = "http://192.168.0.220:9191/#";
        public String goToMainPage(WebDriver driver) {
                driver.get(MAIN_PAGE_ADDRESS);
                driver.manage().window().maximize();
                return driver.getTitle();
        }
        public static String getMainPageAddress() {
                return MAIN_PAGE_ADDRESS;
        }

}
