package test_skenario;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import general.commonFunction;
import general.setDriver;
import general.commonFunction.FailureHandling;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


class bookingData{
    String checkin;
    String checkout;

    public bookingData(String checkin, String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }
}

@TestInstance(Lifecycle.PER_CLASS)
public class roomAvailibility {
    public WebDriver driver;
    public commonFunction func;

    public roomAvailibility(WebDriver driver){
        this.driver = driver;
        func = new commonFunction(driver);
    }

    public void filterAvailibility(bookingData data){
        func.scrollClick("//a[text()='Book Now']");
        func.scrollClick("//*[text()='Check In']//following::input");
        func.setText("//*[text()='Check In']//following::input", Keys.chord(Keys.CONTROL + "A"));
        func.setText("//*[text()='Check In']//following::input", Keys.chord(Keys.BACK_SPACE));
        func.setText("//*[text()='Check In']//following::input", data.checkin);

        func.setText("//*[text()='Check Out']//following::input", Keys.chord(Keys.CONTROL + "A"));
        func.setText("//*[text()='Check Out']//following::input", Keys.chord(Keys.BACK_SPACE));
        func.setText("//*[text()='Check Out']//following::input", data.checkout);
        func.scrollClick("//button[text()='Check Availability']");
    }

    //book room tambah scroll click 
    public void detailRoom(){
        List<WebElement> rooms = driver.findElements(By.xpath("//a[text()='Book now']"));
        List<WebElement> roomNames = driver.findElements(By.xpath("//*[text()='Our Rooms']//following::h5"));
        for(int i=0; i<rooms.size(); i++){
            String expectedRoomName = roomNames.get(i).getText();
            String xpath = "(//a[text()='Book now'])[1]";
            if(i ==0){
                JavascriptExecutor js = (JavascriptExecutor) driver;
                func.delay(Duration.ofSeconds(7));
                js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", rooms.get(i));
            }
            rooms.get(i).click();
            func.waitForElementPresent("//*[@aria-label='breadcrumb']//following::h1", 10);
            String actualRoomName = func.getText("//*[@aria-label='breadcrumb']//following::h1");
            func.verifyMatch(actualRoomName, ".*" + expectedRoomName + ".*", true);
            driver.navigate().back();
        }
    }

    public void reserve(){
        func.scrollClick("//a[text()='Book now']");
        func.delay(Duration.ofSeconds(7));
        func.scrollClick("//*[@id = 'doReservation']");
        func.setText("//input[@name = 'firstname']", "far");
        func.setText("//input[@name = 'lastname']", "ann");
        func.setText("//input[@name='email']", "test@email.com");
        func.setText("//input[@name='phone']", "081112233");
        driver.navigate().back();

    }   


    public void test_tambah_data(){
          driver.get("https://automationintesting.online/");
          driver.manage().window().maximize();
          func.waitForElementPresent("//a[text()='Book now']", 15);
          List<WebElement> rooms = driver.findElements(By.xpath("//a[text()='Book now']"));
           
       rooms.get(0).click();
    }
    
    public void feedback(){
        func.setText("//input[@id = 'name']", "far");
        func.setText("//input[@id = 'email']", "test@gmail.com");
        func.setText("//input[@id = 'phone']", "081222333444");
        func.setText("//input[@id = 'subject']", "test subject");
        WebElement elementDescription = driver.findElement(By.xpath("//*[@id = 'description']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        func.delay(Duration.ofSeconds(7));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", elementDescription);
        func.setText("//*[@id = 'description']", "test message null null test null cash tunai");
        func.scrollClick("//button[.='Submit']");
        func.click("//button[.='Submit']");
        func.verifyElementPresent("//*[contains(text(), 'Thanks for getting in touch')]", 15);
    }


}
