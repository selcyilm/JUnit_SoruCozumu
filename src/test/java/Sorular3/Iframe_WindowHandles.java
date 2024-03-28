package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class Iframe_WindowHandles extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//1- https://testotomasyonu.com/discount adresine gidin
		driver.get("https://testotomasyonu.com/discount");
		String firstHandle = driver.getWindowHandle();

		//2- Elektronics Products yazisinin gorunur olduğunu test edin
		WebElement iFrame1 = driver.findElement(By.xpath("(//iframe)[1]"));

		driver.switchTo().frame(iFrame1);

		WebElement frameTextElement = driver.findElement(By.xpath("//*[text()='Electronics Products']"));
		String actualText = frameTextElement.getText();
		String expectedText = "Electronics Products";

		Assert.assertTrue(frameTextElement.isDisplayed());
		Assert.assertEquals(expectedText, actualText);

		//3- Dell bilgisayar urun isminin ‘DELL Core I3 11th Gen’ olduğunu test edin
		WebElement productNameElement = driver.findElement(By.id("pictext1"));

		String expectedProductName = "DELL Core I3 11th Gen";
		String actualProductName = productNameElement.getText();

		Assert.assertEquals(expectedProductName, actualProductName);

		//4- Dell bilgisayar’a tiklayip acilan sayfada urun fiyatinin 400'den az
		// 200'den fazla olduğunu test edin.
		new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
		ReusableMethods.wait(1);
		productNameElement.click();

		Set<String> windowHandles = driver.getWindowHandles();
		String secondHandle = "";

		for (String eachHandle : windowHandles) {
			if (!eachHandle.equals(firstHandle)) {
				secondHandle = eachHandle;
			}
		}

		driver.switchTo().window(secondHandle);

		WebElement productPrice = driver.findElement(By.id("priceproduct"));
		String productPriceText = productPrice.getText();

		double actualProductPrice = Double.parseDouble(productPriceText.replaceAll("\\D", "")) / 100;

		Assert.assertTrue(actualProductPrice > 200 && actualProductPrice < 400);

		//5- Ilk sayfaya donun ve Fashion yazisinin gorunur olduğunu test edin
		driver.switchTo().window(firstHandle);

		WebElement iFrame2 = driver.findElement(By.xpath("(//iframe)[2]"));

		driver.switchTo().frame(iFrame2);

		WebElement fashionText = driver.findElement(By.xpath("//h2"));

		Assert.assertTrue(fashionText.isDisplayed());
		ReusableMethods.wait(2);

	}
}
