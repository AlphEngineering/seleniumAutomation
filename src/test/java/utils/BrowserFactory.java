package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static WebDriver launchBrowser(String browserName){
        ReadData readData = new ReadData();
        browserName = ReadData.propertyConfig.getProperty("browser");
        WebDriver driver = null;
        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if (browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }else if (browserName.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }else {
            System.out.println("Invalid Browser Name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

}
