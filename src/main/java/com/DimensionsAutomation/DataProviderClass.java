package com.DimensionsAutomation;

import java.util.*;
import org.testng.annotations.DataProvider;

/**
 * Created by 1530155 on 8/15/2017.
 */
public class DataProviderClass {
    @DataProvider(name = "Passing different inputs for hotel search")
    public static Iterator<Object[]> parametriseTestData(){
        LoadTestData inputData = new LoadTestData("MyText_MultiColumn.csv");
        inputData.LoadCSV();
        List<Map<String,String>> dpList = inputData.getToUseDataList();
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for(Map<String, String> map:dpList){
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }
}
