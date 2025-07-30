package general;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;



public class commonFunction {
    public WebDriver driver;

    public commonFunction(WebDriver driver){
        this.driver = driver;
    }

    public enum FailureHandling{
        OPTIONAL, STOP_ON_FAILURE;
    }

  
    public void waitForElementPresent(String xpath, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }catch(NoSuchElementException e){
        }catch(Exception e){
        }
     }

    public void waitForElementPresent(WebElement element, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch(NoSuchElementException e){
        }catch(Exception e){
        }
     }


    public void click(String xpath, FailureHandling handling){    
        try{
            waitForElementPresent(xpath, 10);
            driver.findElement(By.xpath(xpath)).click();
        }catch(ElementClickInterceptedException e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
        }catch(ElementNotInteractableException e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
        }catch(NoSuchElementException e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
        }catch(Exception e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
        }

    }

    public void click(String xpath){
        click(xpath, FailureHandling.STOP_ON_FAILURE);
    }

    public Boolean verifyElementPresent(String xpath, int timeout, FailureHandling handling){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        }catch(NoSuchElementException e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
            return false;
        }catch(Exception e){
            if(handling == FailureHandling.STOP_ON_FAILURE){
                throw new AssertionError(e);
            }
            return false;
        }
    }

    public Boolean verifyElementPresent(String xpath, int timeout){
        return verifyElementPresent(xpath, timeout, FailureHandling.STOP_ON_FAILURE);
    }

    public void setText(String xpath, CharSequence text){
        waitForElementPresent(xpath, 10);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public String getText(String xpath){
        waitForElementPresent(xpath, 10);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void verifyMatch(String actualText, String expectedText){
        verifyMatch(actualText, expectedText, false);
    }

    public void verifyMatch(String actualText, String expectedText, Boolean regex){
        if(regex){
            Assertions.assertEquals(true, actualText.matches(expectedText));
        }else{
            Assertions.assertEquals(expectedText, actualText);
        }
    }
    

    public void scrollToElement(String xpath){
        waitForElementPresent(xpath, 15);
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        click(xpath);
    }

    public void scrollClick(WebElement element){
        
        waitForElementPresent(element, 15);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void scrollClick(String xpath){
       JavascriptExecutor js = (JavascriptExecutor) driver;
       WebElement elementToScroll = driver.findElement(By.xpath(xpath));
       js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", elementToScroll);
       click(xpath);
    }

    

    public void delay(Duration duration){
        try{
            Thread.sleep(duration);
        }catch(Exception e){

        }
    }



}
