package pl.operations;

import pl.config.DataFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    public static String[][] readDbConnectionData() {

        List<String> connectionDataList = new ArrayList<>();
        try {
            Scanner csvReader = new Scanner(new File(DataFiles.DB_CONN_FILE.getDbConnPath()));
            while(csvReader.hasNextLine()) {
                    connectionDataList.add(csvReader.nextLine());
                }
            }
        catch (FileNotFoundException fnfe) {
            System.out.println("Nie odnaleziono pliku " + DataFiles.DB_CONN_FILE.getDbConnPath());
            return null;
        }

        String[][] connectionDataArray = new String[connectionDataList.size()][];
        int i = 0;
        for(String element: connectionDataList) {
            connectionDataArray[i] = element.split(",");
            i++;
        }
        return connectionDataArray;
    }
}
