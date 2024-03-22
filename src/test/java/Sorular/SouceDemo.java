package Sorular;

import Utils.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SouceDemo {

	@Test
	public void urunEklemeTesti() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		//1. “https://www.saucedemo.com” Adresine gidin
		driver.get("https://www.saucedemo.com");

		//2. Username kutusuna “standard_user” yazdirin
		WebElement userNameBox = driver.findElement(By.id("user-name"));
		userNameBox.sendKeys("standard_user");

		//3. Password kutusuna “secret_sauce” yazdirin
		WebElement passwordBox = driver.findElement(By.xpath("//input[@id='password']"));
		passwordBox.sendKeys("secret_sauce");

		//4. Login tusuna basin
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		//5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));

		String firstProductName = productNames.get(0).getText();
		productNames.get(0).click();

		//6. Add to Cart butonuna basin
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

		//7. Alisveris sepetine tiklayin
		driver.findElement(By.className("shopping_cart_link")).click();

		//8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
		String actualProductName = driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']"))
				.getText();
		String expectedProductName = firstProductName;

		Assert.assertTrue("Test Failed", actualProductName.equals(expectedProductName));

		//9. Sayfayi kapatin
		ReusableMethods.wait(2);
		driver.quit();
	}
}
