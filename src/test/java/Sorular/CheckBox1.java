package Sorular;

import Utils.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class CheckBox1 {
	//Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın
	//a. Verilen web sayfasına gidin.
	//https://testotomasyonu.com/form
	//b. Sirt Agrisi ve Carpinti checkbox’larini secin
	//c. Sirt Agrisi ve Carpinti checkbox’larininin seçili
	//olduğunu test edin
	//d. Seker ve Epilepsi checkbox’larininin seçili
	//olmadigini test edin

	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://testotomasyonu.com/form");
	}

	@AfterClass
	public static void teardown() {
		ReusableMethods.wait(2);
		driver.quit();
	}

	//b. Sirt Agrisi ve Carpinti checkbox’larini secin
	//c. Sirt Agrisi ve Carpinti checkbox’larininin seçili
	//olduğunu test edin
	//d. Seker ve Epilepsi checkbox’larininin seçili
	//olmadigini test edin
	@Test
	public void carpintiTest() {
		WebElement epilepsiCheckBox = driver.findElement(By.id("hastalikCheck7"));
		WebElement carpintiCheckBox = driver.findElement(By.id("gridCheck4"));
		WebElement sirtAgrisiCheckBox = driver.findElement(By.id("gridCheck5"));
		new Actions(driver).scrollToElement(epilepsiCheckBox).perform();
		carpintiCheckBox.click();
		sirtAgrisiCheckBox.click();
		boolean isBothSelected = carpintiCheckBox.isSelected() && sirtAgrisiCheckBox.isSelected();
		Assert.assertTrue("Test Failed: Checkboxs Not Selected!", isBothSelected);
	}

	@Test
	public void sekerAndEpilepsiTest() {
		WebElement sekerCheckBox = driver.findElement(By.id("hastalikCheck2"));
		//System.out.println("isDisplayed: " +sekerCheckBox.isDisplayed());
		//System.out.println("isEnabled: " + sekerCheckBox.isEnabled());
		//new Actions(driver).scrollToElement(sekerCheckBox).perform();
		//sekerCheckBox.click();
		WebElement epilepsiCheckBox = driver.findElement(By.id("hastalikCheck7"));
		//new Actions(driver).scrollToElement(epilepsiCheckBox).perform();

		boolean isBothNotSelected = sekerCheckBox.isSelected() || epilepsiCheckBox.isSelected();
		Assert.assertFalse("Test Failed!", isBothNotSelected);
	}
}
