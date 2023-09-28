import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumDemoTest {
    WebDriver driver;

    @Test
    void seleniumDemo() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/maps");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#searchboxinput")).sendKeys("Danang City, Hải Châu District, Da Nang");
        driver.findElement(By.cssSelector("#searchboxinput")).sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector("button[aria-label='Restaurants']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='Restaurants']"))).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
