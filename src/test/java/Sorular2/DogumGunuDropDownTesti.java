package Sorular2;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DogumGunuDropDownTesti extends TestBase_BeforeAfter {

	//https://testotomasyonu.com/form sayfasına gidip
	// dogum gününü 23 Nisan 2006 yapın ve tarihin
	// aynı olduğunu test edin

	@Test
	public void test1() {
		driver.get("https://testotomasyonu.com/form");
		WebElement dayDD = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		//WebElement dayDD = driver.findElement(By.xpath("(//div[@class='col-4'])[1]"));
		Select daySelected = new Select(dayDD);
		daySelected.selectByVisibleText("23");

		WebElement monthDD = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		Select monthSelected = new Select(monthDD);
		monthSelected.selectByValue("nisan");

		WebElement yearDD = driver.findElement(By.xpath("(//select[@class='form-control'])[3]"));
		Select yearSelected = new Select(yearDD);
		yearSelected.selectByIndex(10);
		ReusableMethods.wait(1);

		//günün 23 olduğunu test edin
		String expectedDay = "23";
		String actualDay = daySelected.getFirstSelectedOption().getText();
		Assert.assertEquals("Day Test Failed!", expectedDay, actualDay);

		//ayın Nisan olduğunu test edin
		String expectedMonth = "Nisan";
		String actualMonth = monthSelected.getFirstSelectedOption().getText();
		Assert.assertEquals(expectedMonth, actualMonth);

		//yılın 2006 olduğunu test edin
		int expectedYear = 2006;
		int actualYear = Integer.parseInt(yearSelected.getFirstSelectedOption().getText());
		Assert.assertEquals(expectedYear, actualYear);

		// Secilen değerleri konsolda yazdirin
		System.out.printf("Day: %s, Month: %s, Year: %d\n", expectedDay, expectedMonth, expectedYear);

		//Ay dropdown menüdeki tum değerleri(value) yazdırın
		List<String> ayDropDownList = ReusableMethods.dropDownStringList(monthSelected.getOptions());
		System.out.println(ayDropDownList);

		//Ay Dropdown menusunun boyutunun 13 olduğunu test edin
		int expectedMonthSize = 13;
		int actualMonthSize = ayDropDownList.size();
		Assert.assertEquals(expectedMonthSize, actualMonthSize);
	}
}
