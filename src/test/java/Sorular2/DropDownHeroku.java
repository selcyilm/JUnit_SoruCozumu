package Sorular2;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHeroku extends TestBase_BeforeAfterClass {

	@Test
	public void dropDownTest1() {
		//https://the-internet.herokuapp.com/dropdown adresine gidin.
		driver.get("https://the-internet.herokuapp.com/dropdown");

		//1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
		WebElement dropDownBox = driver.findElement(By.id("dropdown"));

		Select select = new Select(dropDownBox);
		select.selectByIndex(1);
		System.out.println(select.getFirstSelectedOption().getText());
		ReusableMethods.wait(2);

		//2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
		select.selectByValue("2");
		System.out.println(select.getFirstSelectedOption().getText());
		ReusableMethods.wait(2);

		//3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
		select.selectByVisibleText("Option 1");
		System.out.println(select.getFirstSelectedOption().getText());
		ReusableMethods.wait(2);

		//4.Tüm dropdown değerleri(value) yazdırın
		System.out.println(ReusableMethods.dropDownStringList(select.getOptions()));

		//5. Dropdown’un boyutunun 4 olduğunu test edin
		int expectedSize = 4;
		int actualSize = select.getOptions().size();
		Assert.assertEquals("Size test failed, Actual Size: " + actualSize, expectedSize, actualSize);
	}
}
