package Sorular4;

import Utils.TestBase_BeforeAfter;
import org.junit.Test;
import org.openqa.selenium.By;

public class ImplicitWait extends TestBase_BeforeAfter {

	@Test
	public void implicitWaitTest() {
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		for (int i = 0; i < 10; i++) {
			driver.findElement(By.xpath("//*[text()='Remove']")).click();

			driver.findElement(By.xpath("//*[text()='Add']")).click();
		}
	}
}
