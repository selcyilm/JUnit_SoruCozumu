package Sorular3;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SwitchingWindows extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//● https://testotomasyonu.com/addremove/ adresine gidin.
		driver.get("https://testotomasyonu.com/addremove/");
		String firstHandle = driver.getWindowHandle();

		//● Sayfadaki textin “Add/Remove Elements” olduğunu doğrulayın.
		String expectedText = "Add/Remove Elements";
		String actualText = driver.findElement(By.xpath("//*[text()='Add/Remove Elements']"))
				.getText();

		Assert.assertEquals(expectedText, actualText);

		//● Sayfa başlığının(title) “Test Otomasyonu” olduğunu doğrulayın.
		String expectedTitle = "Test Otomasyonu";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(expectedTitle, actualTitle);

		//● ’Please click for Electronics Products’ linkine tiklayin.
		WebElement electronicsButton = driver.findElement(By.linkText("Electronics Products"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", electronicsButton);
		ReusableMethods.wait(1);
		electronicsButton.click();

		//● Electronics sayfasinin acildigini test edin
		ReusableMethods.switchWindowByTitle(driver, "Test Otomasyonu - Electronics");

		String expectedTargetTitle = "Electronics";
		String actualTargetTitle = driver.getTitle();

		Assert.assertTrue(actualTargetTitle.contains(expectedTargetTitle));

		//● Bulunan urun sayisinin 16 olduğunu test edin
		List<WebElement> totalProductList = driver.findElements(By.xpath("//*[@class='prod-title mb-3 ']"));
		int actualTotalSize = totalProductList.size();

		WebElement nextProductPage = driver.findElement(By.xpath("//a[@class='page-link']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextProductPage);
		ReusableMethods.wait(1);
		nextProductPage.click();

		actualTotalSize += driver.findElements(By.xpath("//*[@class='prod-title mb-3 ']")).size();
		int expectedSize = 23;

		Assert.assertEquals(expectedSize, actualTotalSize);
		String lastHandle = driver.getWindowHandle();

		//● Ilk actiginiz addremove sayfasina donun
		ReusableMethods.wait(1);
		driver.switchTo().window(firstHandle);

		//System.out.println("First: " + firstHandle);
		//System.out.println("Last: " + lastHandle);
		//System.out.println(driver.getWindowHandle());
		//System.out.println(driver.getCurrentUrl());
		//● Url’in addremove icerdigini test edin


		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "addremove";

		Assert.assertTrue(actualUrl.contains(expectedUrl));

		ReusableMethods.wait(2);
	}
}
