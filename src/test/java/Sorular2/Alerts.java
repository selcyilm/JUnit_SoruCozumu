package Sorular2;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Alerts extends TestBase_BeforeAfterClass {

	@Test
	public void test1(){
		//- https://testotomasyonu.com/javascriptAlert adresine gidin
		driver.get("https://testotomasyonu.com/javascriptAlert");

		//- 1.alert'e tiklayin
		driver.findElement(By.xpath("(//button[@class='j-button'])[1]"))
				.click();
		//- Alert'deki yazinin "I am a JS Alert" oldugunu test edin
		String actualAlertMessage = driver.switchTo().alert().getText();
		String expectedAlert = "I am a JS Alert";
		Assert.assertEquals(expectedAlert, actualAlertMessage);
		ReusableMethods.wait(2);
		//- OK tusuna basip alert'i kapatin
		driver.switchTo().alert().dismiss();
		ReusableMethods.wait(2);
	}

	@Test
	public void test2() {
		//- https://testotomasyonu.com/javascriptAlert adresine gidin
		//- 2.alert'e tiklayalim
		driver.findElement(By.xpath("//*[text()='Click for JS Confirm']"))
				.click();
		ReusableMethods.wait(1);
		//- Cancel'a basip, cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edin
		driver.switchTo().alert().dismiss();
		ReusableMethods.wait(1);

		WebElement resultText = driver.findElement(By.id("result"));

		String expectedResult = "You clicked: Cancel";
		String actualResult = resultText.getText();

		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void test3() {
		//- 3.alert'e tiklayalim
		WebElement jsPromptButton = driver.findElement(By.xpath("//*[text()='Click for JS Prompt']"));
		new Actions(driver).scrollToElement(jsPromptButton).perform();
		jsPromptButton.click();

		//- Cikan prompt ekranina "Abdullah" yazdiralim
		driver.switchTo().alert().sendKeys("Abdullah");
		ReusableMethods.wait(1);
		//- OK tusuna basarak alert'i kapatalim
		driver.switchTo().alert().accept();


		ReusableMethods.wait(1);
		//- Cikan sonuc yazisinin Abdullah icerdigini test edelim
		WebElement promptResult = driver.findElement(By.id("result"));
		String expectedResult = "Abdullah";
		String actualResult = promptResult.getText();
		Assert.assertTrue(actualResult.contains(expectedResult));

	}
}
