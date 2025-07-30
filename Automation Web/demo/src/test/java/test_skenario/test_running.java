package test_skenario;

import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;

import general.commonFunction;
import general.setDriver;
import test_skenario.roomAvailibility;


@TestInstance(Lifecycle.PER_CLASS)
public class test_running {
    WebDriver driver;
    commonFunction func;
    roomAvailibility room;

    @BeforeAll
    public void setUp(){
        this.driver = setDriver.getDriver();
        this.func = new commonFunction(driver);
        this.room = new roomAvailibility(driver);
        driver.get("https://automationintesting.online/");
        driver.manage().window().maximize();
        func.delay(Duration.ofSeconds(7));
    }

    @Test
    public void test_full(){
        bookingData data = new bookingData("09/07/2025", "10/07/2025");
        room.filterAvailibility(data);
        room.detailRoom();
        room.reserve();
        room.feedback();
    }



    @Test
    public void test_reserve(){
        room.reserve(); 
    }

    @Test
    public void test_feedback(){
        room.feedback(); 
    }

    @Test
    public void test_book(){
        bookingData data = new bookingData("09/07/2025", "10/07/2025");
        room.filterAvailibility(data);
        room.detailRoom();
    }


}
