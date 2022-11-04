package pl.config;

/*

This object is responsible for gathering paths to data files used in tests, e.g. csv files where DB connection
data is being stored.

 */

public enum DataFiles {

    DB_CONN_FILE(System.getProperty("user.dir")+"\\src\\test\\test-input-data\\db-connections\\SettingsData.csv"),
    SCREENSHOTS_DIR(System.getProperty("user.dir")+"\\src\\test\\tests-screenshots\\");
    private final String filePath;
    private DataFiles(String filePath) {
        this.filePath = filePath;
    }
    public String getDbConnPath() {
        return DB_CONN_FILE.filePath;
    }
    public String getScreenshotPath() { return SCREENSHOTS_DIR.filePath ;}
}
