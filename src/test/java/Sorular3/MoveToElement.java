package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MoveToElement extends TestBase_BeforeAfter {

	@Test
	public void moveToElementTest() {
		//1- https://www.testotomasyonu.com/ adresine gidin
		driver.get("https://www.testotomasyonu.com/");

		//2- “Kids Wear” menusunun acilmasi icin mouse’u bu menunun ustune getirin
		Actions actions = new Actions(driver);
		ReusableMethods.wait(1);

		WebElement kidsWearLinkElement = driver.findElement(By.xpath("(//a[text()='Kids Wear'])[3]"));

		actions.moveToElement(kidsWearLinkElement).perform();

		//3- “Boys” linkine basin
		driver.findElement(By.linkText("Boys")).click();

		//4- Acilan sayfadaki ilk urunu tiklayin
		actions.sendKeys(Keys.PAGE_DOWN).perform();

		driver.findElement(By.xpath("//*[@class='prod-title mb-3 ']")).click();
		ReusableMethods.wait(1);

		//5- Acilan sayfada urun isminin “Boys Shirt White Color” oldugunu test edin
		String expectedProductName = "Boys Shirt White Color";
		String actualProductName = driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"))
				.getText();

		Assert.assertEquals(expectedProductName, actualProductName);
	}
}
