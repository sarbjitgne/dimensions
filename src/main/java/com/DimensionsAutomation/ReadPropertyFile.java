package com.DimensionsAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private Properties propFile = new Properties();

    public Properties getPropFile() {
        return propFile;
    }
    public void readPropertyFile(){
        File fileToRead = new File(System.getProperty("user.dir")+"\\configuration\\config.properties");
        FileInputStream fileIPStream = null;
        try{
            fileIPStream = new FileInputStream(fileToRead);
            propFile.load(fileIPStream);

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fileIPStream!=null){
                try{
                    fileIPStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }

            }
        }

    }
}
