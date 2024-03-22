package Sorular;

import Utils.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BestBuyAssertions {
	/*
		1) Bir class oluşturun: BestBuyAssertions
		2) https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak
			asagidaki testleri yapin
	*/
	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.bestbuy.com/");
	}

	@AfterClass
	public static void tearDown() {
		ReusableMethods.wait(3);
		driver.quit();
	}

	@Test
	public void urlTest() {
		//○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
		String expectedUrl = "https://www.bestbuy.com/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals("Url Test Failed", expectedUrl, actualUrl);
	}

	@Test
	public void titleTest() {
		//○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
		String expectetTitle = "Rest";
		String actualTitle = driver.getTitle();
		Assert.assertFalse("Title Test Contains " + expectetTitle ,actualTitle.contains(expectetTitle));
	}


	@Test
	public void logoTest() {
		//○ logoTest => BestBuy logosunun görüntülendigini test edin
		WebElement logoImg = driver.findElement(By.xpath("//img[@class='logo']"));
		Assert.assertTrue("Test Failed, Img is not Displayed", logoImg.isDisplayed());
	}

	@Test
	public void francaisLinkTest() {
		//○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin
		WebElement frenchLangButton = driver.findElement(By.xpath("//button[text()='Français']"));
		Assert.assertTrue("Test Failed: French Button is not Displayed!", frenchLangButton.isDisplayed());
	}
}
