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

public class DragAndDrop1 extends TestBase_BeforeAfter {

	@Test
	public void test1() {
		//1- https://demoqa.com/droppable adresine gidelim
		driver.get("https://demoqa.com/droppable");

		//2- “Drag me” butonunu tutup “Drop here” kutusunun ustune birakalim
		Actions actions = new Actions(driver);
		ReusableMethods.wait(1);

		WebElement dragMeButton = driver.findElement(By.id("draggable"));
		WebElement dropHereBox = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		ReusableMethods.wait(3);
		actions.dragAndDrop(dragMeButton, dropHereBox).perform();

		ReusableMethods.wait(2);

		//3- “Drop here” yazisi yerine “Dropped!” oldugunu test edin
		String expectedResult = "Dropped!";
		String actualResult = driver.findElement(By.xpath("(//div[@id='droppable'])[1]/p"))
				.getText();

		Assert.assertEquals(expectedResult, actualResult);

	}
}
