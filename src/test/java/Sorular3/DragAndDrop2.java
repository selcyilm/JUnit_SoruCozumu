package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop2 extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//1- https://testotomasyonu.com/droppable adresine gidelim
		driver.get("https://testotomasyonu.com/droppable");

		//2- Prevent Propogation bolumunde “Drag Me” butonunu tutup “Inner droppable (not greedy)”
		//kutusunun ustune birakalim
		Actions actions = new Actions(driver);
		ReusableMethods.wait(1);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		ReusableMethods.wait(1);


		WebElement dragMeButton = driver.findElement(By.id("draggable3"));
		WebElement notGreedyBox = driver.findElement(By.id("droppable-inner3"));

		actions.dragAndDrop(dragMeButton, notGreedyBox).perform();
		ReusableMethods.wait(3);

		//3- “Outer droppable” yazisi yerine “Dropped!” oldugunu test edin
		String expectedResult1 = "Dropped!";
		String actualResult1 = driver.findElement(By.xpath("//div[@id='droppable3']/p"))
				.getText();

		Assert.assertEquals(expectedResult1, actualResult1);

		//4- Sayfayi yenileyin
		driver.navigate().refresh();
		ReusableMethods.wait(2);

		//actions.sendKeys(Keys.PAGE_DOWN).perform();
		ReusableMethods.wait(2);
		//5- "Drag Me"butonunu tutup "Inner droppable (greedy)" kutusunun ustune
		//birakalim
		dragMeButton = driver.findElement(By.id("draggable3"));
		WebElement greedyBox = driver.findElement(By.id("droppable4-inner"));

		actions.dragAndDrop(dragMeButton, greedyBox).perform();
		ReusableMethods.wait(1);

		//6- “Outer droppable” yazisinin degismedigini test edin
		String expectedResult2 = "Outer droppable";
		String actualResult2 = driver.findElement(By.xpath("//div[@id='droppable4']/p"))
				.getText();

		Assert.assertEquals(expectedResult2, actualResult2);

	}
}
