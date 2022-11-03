package pl.config;


public abstract class AppPages {
        private static final String MAIN_PAGE_ADDRESS = "http://192.168.0.220:9191/#";
        public abstract String goToMainPage();
        public static String getMainPageAddress() {
                return MAIN_PAGE_ADDRESS;
        }

}
