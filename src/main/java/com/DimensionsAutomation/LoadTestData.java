package com.DimensionsAutomation;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by 1530155 on 8/7/2017.
 */
public class LoadTestData {
    private Scanner fileReader = null;
    private List<Map<String, String>> toUseDataList = new ArrayList<Map<String, String>>();
    private String fileToRead; //= "MyText_MultiColumn.csv";

    public LoadTestData(String fileToRead) {
        this.fileToRead = System.getProperty("user.dir")+"\\src\\main\\resources\\test_data\\"+fileToRead;
    }

    public List<Map<String, String>> getToUseDataList() {
        return toUseDataList;
    }

    public void LoadCSV() {
        String[] rowHeader;
        List<String> rowHeaderList;
        String[] rowData;
        List<String> rowDataList;
        try {
            fileReader = new Scanner(new FileReader(fileToRead));
            String headers = fileReader.nextLine();
            rowHeader = headers.split(",");
            rowHeaderList = Arrays.asList(rowHeader);
            while (fileReader.hasNext()) {
                String row = fileReader.nextLine();
                rowData = row.split(",");
                rowDataList = Arrays.asList(rowData);
                Map<String, String> dataMap = new HashMap<String, String>();
                for (int i = 0; i < rowHeaderList.size(); i++) {
                    dataMap.put(rowHeaderList.get(i), rowDataList.get(i));
                }
                toUseDataList.add(dataMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }

    }
}
