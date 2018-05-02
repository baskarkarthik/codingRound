import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.WebElement;
public class SignInTest {

    WebDriver driver = null;
    @BeforeTest
    public void preCondition()
     {
    	setDriverPath();
        driver = new ChromeDriver();  //Initialised driver after setting driver path
        driver.get("https://www.cleartrip.com/");
     }
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

    	waitFor(2000);
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        WebElement e = driver.findElement(By.id("modal_window"));
        driver.switchTo().frame(e);  //Included switchToFrame
        driver.findElement(By.id("signInButton")).click();
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.switchTo().defaultContent();
    }
    
    @AfterTest //Added After test for quit browser after test
    public void postCondition()
    {
    	driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

   

}
