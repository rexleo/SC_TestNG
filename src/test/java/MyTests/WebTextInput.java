package MyTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class WebTextInput {
    @DataProvider
    public Object[][] dataSet(){
        return new Object[][]
                {
                        {"Jolly Rabbit"},
                        {"Fighter Pilot"},
                        {"Kruger Lion"},
                };
    }
    public static int i=0;
    @Test(priority = 1, dataProvider = "dataSet")
    public void changeTextInputButton(String mybuttonname)
    {
        i=i+1;
        Reporter.log("Starting WebInputButton tests");
        SoftAssert softAssert = new SoftAssert();
        String expTitle = "UI Test Automation Playground";
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        System.out.println("Browser Opened. Initiating tests...");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.uitestingplayground.com/"); //enter URL
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle,expTitle); // Verify page title. Stop test when title is unexpected

        // Navigate to text output page and enter text
        driver.findElement(By.linkText("Text Input")).click();
        String buttonInitialText = driver.findElement(By.id("updatingButton")).getText(); //Get text before changing name
        String buttonExpText = mybuttonname;
        System.out.println(buttonInitialText);
        driver.findElement(By.id("newButtonName")).sendKeys(buttonExpText); //Enter new text
        driver.findElement(By.id("updatingButton")).click();
        String newButtonText = driver.findElement(By.id("updatingButton")).getText(); //Get new button text
        softAssert.assertEquals(newButtonText,buttonExpText,"Button name change failed!");
        if(i>2) {
            //Force a fail to test
            softAssert.assertEquals(newButtonText,"Humpty Dumpty","Button name change failed!");
        }

        driver.close();
        softAssert.assertAll(); //Report all failures
        System.out.println("...Browser closed. Tests complete.");


    }



}
