package Sorular4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitlyWait {

	@Test
	public void test01() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		//3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		//4. Remove butonuna basin.

		driver.findElement(By.xpath("//*[text()='Remove']")).click();

		//5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement messageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

		Assert.assertTrue(messageText.isDisplayed());

		//6. Add buttonuna basin
		driver.findElement(By.xpath("(//button)[1]")).click();


		//7. It’s back mesajinin gorundugunu test edin
		WebElement itsBackText = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()=\"It's back!\"]")));

		Assert.assertTrue(itsBackText.isDisplayed());

		driver.quit();
	}
}
