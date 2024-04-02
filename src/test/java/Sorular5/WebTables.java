package Sorular5;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTables extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//1."https://testotomasyonu.com/webtables" adresine gidin
		driver.get("https://testotomasyonu.com/webtables");

		//2.Web table tum body’sini yazdirin
		WebElement bodyElement = driver.findElement(By.tagName("tbody"));

		System.out.println(bodyElement.getText());
		System.out.println("=======================\n\n");
		//3. Web tablosunda "Comfortable Gaming Chair" bulundugunu test edin
		String expectedChairText = "Comfortable Gaming Chair";
		String actualChairText = bodyElement.getText();

		Assert.assertTrue(actualChairText.contains(expectedChairText));

		//4. Web table’daki satir sayisinin 5 oldugunu test edin
		List<WebElement> rowElements = driver.findElements(By.xpath("//tbody/tr"));

		int expectedRowSize = 5;
		int actualRowSize = rowElements.size();

		Assert.assertEquals(expectedRowSize, actualRowSize);

		//5. Tum satirlari yazdirin
		List<String> rowElementsList = ReusableMethods.dropDownStringList(rowElements);

		System.out.println(rowElementsList);
		System.out.println("=======================\n\n");

		//6. Web table’daki sutun sayisinin 4 olduğunu test edin
		int expectedColumnSize = 4;
		int actualColumnSize = driver.findElements(By.xpath("(//tbody/tr)[3]/td"))
				.size();

		Assert.assertEquals(expectedColumnSize, actualColumnSize);

		//7. 3.sutunu yazdirin
		List<WebElement> thirdColumElements = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		List<String> thirdColElementsList = ReusableMethods.dropDownStringList(thirdColumElements);

		System.out.println(thirdColElementsList);
		System.out.println("=======================\n\n");

		//8. Tablodaki basliklari yazdirin
		WebElement theadElement = driver.findElement(By.tagName("thead"));

		System.out.println(theadElement.getText());
		System.out.println("=======================\n\n");

		//9. Satir ve sutun sayisini parametre olarak alip, hucredeki bilgiyi
		//döndüren bir method olusturun
		System.out.println(getData(3, 4));
		System.out.println(getData(1, 1));
		System.out.println(getData(2, 3));

		//10. 4.satirdaki category degerinin "Furniture" oldugunu test edin
		//1. yol, eğer satır ve sütün değerini biliyorsak
		String expectedCategory = "Furniture";
		String actualCategory = getData(4, 2);

		Assert.assertEquals(expectedCategory, actualCategory);

		//2. yol: sadece satır değerini biliyorsak
		List<WebElement> fourthRowElement = driver.findElements(By.xpath("//tbody/tr[4]/td"));
		boolean flag = false;

		for (WebElement each : fourthRowElement) {
			String eachText = each.getText();
			if (eachText.equals(expectedCategory)) {
				flag = true;
				break ;
			}
		}
		Assert.assertTrue(flag);
	}
	public String getData(int satirSayisi, int sutunSayisi) {
		String dinamicXpath = "//tbody/tr["+ satirSayisi +"]/td[" + sutunSayisi + "]";

		WebElement cellElement = driver.findElement(By.xpath(dinamicXpath));

		return (cellElement.getText());
	}
}
