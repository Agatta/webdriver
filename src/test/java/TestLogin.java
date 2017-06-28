import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by askorska on 2017-06-28.
 */
public class TestLogin {
private static WebDriver driver;

    public  boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return  true;
        }
        catch (NoSuchElementException e) {
            return false;
        }

    }
    @BeforeClass
    public static  void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TestLogin() {
        driver.navigate().to("https://testingcup.pgs-soft.com/task_6");
        Assert.assertEquals("Welcome!", driver.getTitle());

        WebElement userNameInput = driver.findElement(By.id("LoginForm__username"));
        WebElement userPasswordInput = driver.findElement(By.id("LoginForm__password"));
        userNameInput.sendKeys("tester");
        userPasswordInput.sendKeys("123-xyz");
        driver.findElement(By.id("LoginForm_save")).click();
        Assert.assertTrue(isElementPresent(By.id("logout")));
        Assert.assertEquals("Wyloguj", driver.findElement(By.id("logout")).getText());

    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
