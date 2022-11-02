package pl.config;

import org.openqa.selenium.WebDriver;

public interface AppPages {

        static final String MAIN_PAGE = "http://192.168.0.220:9191";
        public String goToPage();
        public void closePage();

}
