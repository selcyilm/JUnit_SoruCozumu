package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;

public class EventAddTest extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//https://weblab.tudelft.nl/docs/cse1110/639bcbc2dfac2ed854e7950561373ed4/ adresine gidin
		driver.get("https://weblab.tudelft.nl/docs/cse1110/639bcbc2dfac2ed854e7950561373ed4/");

		// 10:00 ile 13:00 arasında "soru cozumu" isimli event ekleyin
		Actions actions = new Actions(driver);
		ReusableMethods.wait(1);

		driver.findElement(By.id("from")).click();

		actions.sendKeys("10:00")
				.sendKeys(Keys.TAB)
				.sendKeys("13:00")
				.sendKeys(Keys.TAB)
				.keyDown(Keys.SHIFT)
				.sendKeys("S")
				.keyUp(Keys.SHIFT)
				.sendKeys("oru ")
				.keyDown(Keys.SHIFT)
				.sendKeys("c")
				.keyUp(Keys.SHIFT)
				.sendKeys("ozumu")
				.sendKeys(Keys.TAB)
				.sendKeys(Keys.ENTER)
				.perform();
		ReusableMethods.wait(2);

		//eklenen eventin aynı olduğunu test edin
		String expectedEventName = "Soru Cozumu";
		String actualEventName = driver.findElement(By.xpath("//*[text()='Soru Cozumu']"))
				.getText();

		Assert.assertEquals(expectedEventName, actualEventName);

		//aynı saate yeni event ekleyin
		WebElement descriptionBox = driver.findElement(By.id("description"));
		descriptionBox.clear();
		descriptionBox.sendKeys("Grup Calismasi" + Keys.TAB + Keys.ENTER);

		ReusableMethods.wait(2);

		//"This event should not overlap with an existing event" errorunun çıktığını
		//test edin
		String expectedErrorMsg = "This event should not overlap with an existing event";
		String actualErrorMsg = driver.findElement(By.id("alert-msg")).getText();

		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
	}
}
