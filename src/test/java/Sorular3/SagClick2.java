package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SagClick2 extends TestBase_BeforeAfter {

	@Test
	public void sagClickTest() {
		//2- https://the-internet.herokuapp.com/context_menu sitesine gidin
		driver.get("https://the-internet.herokuapp.com/context_menu");

		//3- Cizili alan uzerinde sag click yapin
		WebElement areaToClickElement = driver.findElement(By.id("hot-spot"));

		Actions actions = new Actions(driver);
		ReusableMethods.wait(1);

		actions.contextClick(areaToClickElement).perform();

		//4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edin.
		String expectedAlertMsg = "You selected a context menu";
		String actualAlertMsg = driver.switchTo().alert().getText();

		Assert.assertEquals(expectedAlertMsg, actualAlertMsg);

		//5- Tamam diyerek alert’i kapatalim
		driver.switchTo().alert().accept();

		//6- Elemental Selenium linkine tiklayalim
		driver.findElement(By.xpath("//*[text()='Elemental Selenium']")).click();

		ReusableMethods.switchWindowByTitle(driver, "Home | Elemental Selenium");

		//7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim
		String expectedH1 = "Elemental Selenium";
		String actualH1 = driver.findElement(By.tagName("h1")).getText();

		Assert.assertEquals(expectedH1, actualH1);

	}
}
