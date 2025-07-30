package general;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class setDriver {
    public static WebDriver getDriver(){
        String path = System.getProperty("user.dir").replace("\\", "/");
        System.setProperty("chrome.driver.webdriver", "path/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver; 
    }
}
