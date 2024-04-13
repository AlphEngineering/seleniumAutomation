package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadData {
    public static Properties property = new Properties();
    public static Properties propertyConfig = property;
    public static Properties propertyCustomer = property;
    public static Properties propertyMerchant = property;

    public ReadData(){
        try {
            FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"properties"+File.separator+"config.properties");
            FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"properties"+File.separator+"customer.properties");
            FileInputStream fis3 = new FileInputStream(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"properties"+File.separator+"merchant.properties");
            property.load(fis1);
            property.load(fis2);
            property.load(fis3);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e1) {
            e1.printStackTrace();
        }
    }
}
