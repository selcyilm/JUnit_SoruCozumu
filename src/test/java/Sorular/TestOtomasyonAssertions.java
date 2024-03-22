package Sorular;

import Utils.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestOtomasyonAssertions {
		//1) Bir class oluşturun: YoutubeAssertions
		//2) https://www.testotomasyonu.com adresine gidin
		//3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin

	private WebDriver driver;
	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.testotomasyonu.com");
	}

	@After
	public void tearDown() {
		ReusableMethods.wait(1);
		driver.quit();
	}

	@Test
	@Ignore
	public void titleTest() {
		//○ titleTest => Sayfa başlığının “Test Otomasyonu” oldugunu test edin
		String expectedTitle = "Test Otomasyon";
		String actualTitle = driver.getTitle();
		Assert.assertEquals("Test Failed: Titles not same!", expectedTitle, actualTitle);
	}

	@Test
	public void imageTest() {
		//○ imageTest => Test Otomasyon resminin görüntülendiğini (isDisplayed()) test edin
		WebElement logoImg = driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
		Assert.assertTrue("Test Failed: Logo is not Displayed!", logoImg.isDisplayed());
	}
	@Test
	public void searchBoxTest() {
		//○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
		WebElement searchBox = driver.findElement(By.id("global-search"));
		Assert.assertTrue("Test Failed: SeachBox is not enabled!", searchBox.isEnabled());
	}

	//○ wrongTitleTest => Sayfa basliginin “Test Otomasyonu” olmadigini dogrulayin
	@Test
	public void wrongTitleTest() {
		//○ titleTest => Sayfa başlığının “Test Otomasyonu” oldugunu test edin
		String unExpectedTitle = "test otomasyonu";
		String actualTitle = driver.getTitle();
		Assert.assertNotEquals("Test Failed: Titles are Same!", unExpectedTitle, actualTitle);
	}
}
