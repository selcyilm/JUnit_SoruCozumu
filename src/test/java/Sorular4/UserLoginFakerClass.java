package Sorular4;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.List;
import java.util.Random;

public class UserLoginFakerClass extends TestBase_BeforeAfter {

	@Test
	public void userLogin() {
		//https://www.automationexercise.com/ sayfasına gidin
		driver.get("https://www.automationexercise.com/");

		// Sign Up/ Login linkine tıklayın

		driver.findElement(By.xpath("//*[text()=' Signup / Login']")).click();

		// "New User Signup!" yazısının göründüğünü test edin

		WebElement newUserText = driver.findElement(By.xpath("//*[text()='New User Signup!']"));

		Assert.assertTrue(newUserText.isDisplayed());

		// name ve email alanına bilgileri girip signUp butonuna tıklayın

		Faker faker = new Faker();
		String eMail = faker.internet().emailAddress();
		String fullName = faker.name().fullName();

		WebElement nameArea = driver.findElement(By.xpath("//input[@type='text']"));
		nameArea.sendKeys(fullName, Keys.TAB, eMail, Keys.TAB, Keys.ENTER);

		ReusableMethods.wait(2);

		//sayfadaki bilgileri doldurun

		//mr
		driver.findElement(By.id("id_gender1")).click();

		String passWord = faker.internet().password();

		WebElement passwordBox = driver.findElement(By.id("password"));

		passwordBox.sendKeys(passWord);

		WebElement dayDD = driver.findElement(By.id("days"));

		Select selectDay = new Select(dayDD);
		selectDay.selectByVisibleText("17");

		WebElement monthDD = driver.findElement(By.xpath("(//select)[2]"));

		new Select(monthDD).selectByValue("4");

		WebElement yearDD = driver.findElement(By.xpath("(//select)[3]"));

		new Select(yearDD).selectByVisibleText("1983");

		driver.findElement(By.id("optin")).click();

		WebElement firstNameBox = driver.findElement(By.id("first_name"));

		String nameArr[] = fullName.split(" ");
		String firstName = "";
		String lastName = "";

		if (nameArr.length == 3) {
			firstName = nameArr[0] + " " + nameArr[1];
			lastName = nameArr[2];
		}
		else if (nameArr.length == 2) {
			firstName = nameArr[0];
			lastName = nameArr[1];
		}

		Actions actions = new Actions(driver);

		actions.click(firstNameBox)
				.sendKeys(firstName)
				.sendKeys(Keys.TAB)
				.sendKeys(lastName)
				.sendKeys(Keys.TAB)
				.sendKeys(faker.company().name())
				.sendKeys(Keys.TAB)
				.sendKeys(faker.address().fullAddress())
				.sendKeys(Keys.TAB)
				.sendKeys(faker.address().fullAddress())
				.perform();

		WebElement countryDD = driver.findElement(By.xpath("(//select)[4]"));
		new Select(countryDD).selectByValue("United States");

		WebElement stateBox = driver.findElement(By.id("state"));

		actions.click(stateBox)
				.sendKeys(faker.address().state(), Keys.TAB)
				.sendKeys(faker.address().cityName(), Keys.TAB)
				.sendKeys(faker.address().zipCode(), Keys.TAB)
				.sendKeys(faker.phoneNumber().cellPhone(), Keys.TAB, Keys.ENTER)
				.perform();

		// "ACCOUNT CREATED!" yazısının görüntülendiğini test edin

		WebElement accountCreatedText = driver.findElement(By.xpath("//*[text()='Account Created!']"));

		Assert.assertTrue(accountCreatedText.isDisplayed());

		//continue buttonuna tıklayın
		driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();

		//logout yapın
		driver.findElement(By.xpath("//*[text()=' Logout']")).click();

		// email ve şifre ile giriş yapalım
		WebElement emailBoxForLogin = driver.findElement(By.xpath("//input[@data-qa='login-email']"));

		emailBoxForLogin.sendKeys(eMail, Keys.TAB, passWord, Keys.TAB, Keys.ENTER);

		//İlk ürüne tıklayıp sepete atın

		driver.findElement(By.xpath("(//*[text()='View Product'])[1]")).click();

		driver.findElement(By.xpath("//*[@class='btn btn-default cart']")).click();

		//"Added!" yazısının ekranda göründüğünü test edin

		WebElement addedText = driver.findElement(By.xpath("//*[@class='modal-title w-100']"));
		ReusableMethods.wait(1);

		Assert.assertTrue(addedText.isDisplayed());

		//view cart'a tıklayalım

		driver.findElement(By.linkText("View Cart")).click();

		//eklediğiniz ürünün fiyatinin 500 olduğunu test edin
		String productPrice = driver.findElement(By.className("cart_total_price")).getText();

		int expectedPrice = 500;
		int actualPrice = Integer.parseInt((productPrice.split(" "))[1]);

		Assert.assertEquals(expectedPrice, actualPrice);

		//"Proceed To Checkout" linkine tıklayın
		driver.findElement(By.linkText("Proceed To Checkout")).click();

		//comment alanına sipariş hakkında mesaj gönderin
		WebElement commentAreaBox = driver.findElement(By.className("form-control"));
		commentAreaBox.sendKeys(faker.lorem().paragraph(4), Keys.TAB, Keys.ENTER);

		//kart bilgilerini girin

		WebElement cardNameBox = driver.findElement(By.xpath("//*[@name='name_on_card']"));

		actions.click(cardNameBox)
				.sendKeys(fullName, Keys.TAB)
				.sendKeys(faker.business().creditCardNumber(), Keys.TAB)
				.sendKeys(faker.number().digits(3), Keys.TAB)
				.sendKeys("02")
				.sendKeys(Keys.TAB)
				.sendKeys("2025", Keys.TAB)
				.sendKeys(Keys.ENTER)
				.perform();

		//"ORDER PLACED!" yazısının göründüğünü test edin
		WebElement orderPlacedText = driver.findElement(By.xpath("//*[text()='Order Placed!']"));

		Assert.assertTrue(orderPlacedText.isDisplayed());

		//Download Invoice linkine tıklayıp indirilen dosyanın var olduğunu test edin
		driver.findElement(By.linkText("Download Invoice")).click();

		String filePath = "C:/Users/avsel/Downloads/invoice.txt";
		ReusableMethods.wait(2);

		Assert.assertTrue(Files.exists(Paths.get(filePath)));

		//delete account linkinden hesabı silin ve silindiğini test edin

		driver.findElement(By.xpath("//*[text()=' Delete Account']")).click();

		WebElement accDeleted = driver.findElement(By.xpath("//*[text()='Account Deleted!']"));

		Assert.assertTrue(accDeleted.isDisplayed());

		//signup / login kısmından silinen bilgilerle giriş yapmaya çalışıp
		//hesaba girilemediğini test edin
		driver.findElement(By.xpath("//*[text()=' Signup / Login']")).click();

		emailBoxForLogin = driver.findElement(By.xpath("//input[@data-qa='login-email']"));

		emailBoxForLogin.sendKeys(eMail, Keys.TAB, passWord, Keys.TAB, Keys.ENTER);

		WebElement invalidLoginMsg = driver.findElement(By.xpath("//*[contains(text(), 'incorrect!')]"));

		Assert.assertTrue(invalidLoginMsg.isDisplayed());

	}
}
