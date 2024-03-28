package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SagClick extends TestBase_BeforeAfterClass {

	@Test
	public void sagClickTest() {
		//1- https://testotomasyonu.com/click sitesine gidin
		driver.get("https://testotomasyonu.com/click");

		//2- “DGI Drones” uzerinde sag click yapin
		ReusableMethods.wait(1);

		WebElement droneLinkBox = driver.findElement(By.id("pic2_thumb"));

		Actions actions = new Actions(driver);
		actions.contextClick(droneLinkBox).perform();
		//ReusableMethods.wait(1);
		//3- Alert’te cikan yazinin “Tebrikler!... Sağ click yaptınız.” oldugunu test edin.
		String expectedAlertMsg = "Tebrikler!... Sağ click yaptınız.";
		String actualAlertMsg = driver.switchTo().alert().getText();

		Assert.assertEquals(expectedAlertMsg, actualAlertMsg);

		//4- Tamam diyerek alert’i kapatalim
		driver.switchTo().alert().accept();
		//ReusableMethods.wait(1);

	}
}
