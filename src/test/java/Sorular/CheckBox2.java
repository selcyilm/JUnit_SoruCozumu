package Sorular;

import Utils.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

public class CheckBox2 {
	/*Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
	a. Verilen web sayfasına gidin.
	https://the-internet.herokuapp.com/checkboxes
	b. Checkbox1 ve checkbox2 elementlerini locate edin.
	c. Checkbox1 seçili değilse onay kutusunu tıklayın
	d. Checkbox2 seçili değilse onay kutusunu tıklayın
	e. Checkbox1 ve Checkbox2’nin seçili olduğunu test edin
	*/
	@Test
	public void checkBoxTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		//a. Verilen web sayfasına gidin.
		//https://the-internet.herokuapp.com/checkboxes
		driver.get("https://the-internet.herokuapp.com/checkboxes");

		//b. Checkbox1 ve checkbox2 elementlerini locate edin.
		WebElement checkBox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
		WebElement checkBox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));

		ReusableMethods.wait(1);

		int time = new Random().nextInt(10);
		for (int i = 0; i < time; i++) {
			checkBox1.click();
		}

		time = new Random().nextInt(10);
		for (int i = 0; i < time; i++) {
			checkBox2.click();
		}

		ReusableMethods.wait(1);
		//c. Checkbox1 seçili değilse onay kutusunu tıklayın
		if (!checkBox1.isSelected())
			checkBox1.click();

		//d. Checkbox2 seçili değilse onay kutusunu tıklayın
		if (!checkBox2.isSelected())
			checkBox2.click();

		//e. Checkbox1 ve Checkbox2’nin seçili olduğunu test edin
		boolean isBothSelected = checkBox2.isSelected() && checkBox1.isSelected();
		Assert.assertTrue("Test Failed!", isBothSelected);
		ReusableMethods.wait(3);
		driver.quit();
	}
}
