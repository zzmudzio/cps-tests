package pl.config;

public enum DataFiles {

    DB_CONN_DATA(System.getProperty("user.dir")+"/src/test/test-data/db-connections/SettingsData.csv");
    private final String filePath;
    private DataFiles(String filePath) {
        this.filePath = filePath;
    }

}
