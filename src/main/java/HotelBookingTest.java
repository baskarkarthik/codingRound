import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.util.List;
import org.openqa.selenium.support.How;
 
import org.openqa.selenium.support.PageFactory;

public class HotelBookingTest {

	static WebDriver driver = null;  // All varibles and fns are declared as static

    @FindBy(linkText = "Hotels")
    static WebElement hotelLink;

    @FindBy(id = "Tags")
    static WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    static WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    static WebElement travellerSelection;
    
    @BeforeTest
    public void preCondition()
     {
    	setDriverPath();
        driver = new ChromeDriver();  //Initialised driver after setting driver path
        driver.get("https://www.cleartrip.com/");
     }
   
    @Test
    public static void shouldBeAbleToSearchForHotels() {
    	    	
        PageFactory.initElements(driver, HotelBookingTest.class); //Called initElements to initalize driver elements
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        waitFor(5000);
        driver.findElement(By.xpath("//a[text()='Indiranagar, Bangalore, Karnataka, India']")).click();
        waitFor(2000);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[6]/a")).click();
        waitFor(2000);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
    }
    
    @AfterTest //Added After test for quit browser after test
    public void postCondition()
    {
    	driver.quit();
    }

    public static void setDriverPath() {
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
    
    public static void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
