package Sorular5;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTables2 extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//1. “https://testotomasyonu.com/webtables2” sayfasina gidin
		driver.get("https://testotomasyonu.com/webtables2");

		//2. Headers da bulunan basliklari yazdirin
		WebElement baslikElement = driver.findElement(By.xpath("//*[@*='hrow']"));

		System.out.println(baslikElement.getText());

		//3. 3.sutunun basligini yazdirin
		String baslikUcuncuSutunText = driver.findElement(By.xpath("//div[@role='hdata'][3]"))
				.getText();

		System.out.println(baslikUcuncuSutunText);

		//4. Tablodaki tum datalari yazdirin
		List<WebElement> allDataElements = driver.findElements(By.xpath("//*[@*='tdata']"));
		List<String> allDataList = ReusableMethods.webElementTostringList(allDataElements);

		System.out.println(allDataList);

		//5. Tabloda kac tane cell (data) oldugunu yazdirin
		int allCellSize = allDataList.size();

		System.out.println("Data Sayısı: " + allCellSize);

		//6. Tablodaki satir sayisini yazdirin
		List<WebElement> satirElementleri = driver.findElements(By.xpath("//*[@*='trow']"));

		System.out.println("Satır sayısı: " + satirElementleri.size());

		//7. Tablodaki sutun sayisini yazdirin
		List<WebElement> sutunElementleri = driver.findElements(By.xpath("//div[@role='trow'][3]/div[@role='tdata']"));

		System.out.println("Sutun sayısı: " + sutunElementleri.size());

		//8. Tablodaki 3.kolonu yazdirin
		List<WebElement> ucuncuKolonElementleri = driver.findElements(By.xpath("//div[@role='trow']/div[@role='tdata'][3]"));
		List<String> ucuncuKolonList = ReusableMethods.webElementTostringList(ucuncuKolonElementleri);

		System.out.println(ucuncuKolonList);

		//9. Tabloda " Category" si Furniture olan urunun fiyatini yazdirin
		for (int i = 1; i <= satirElementleri.size(); i++) {
			String categoryXpath = "//div[@role='trow']["+ i +"]/div[@role='tdata'][2]";
			String priceXpath = "//div[@role='trow']["+ i +"]/div[@role='tdata'][3]";

			String categoryValue = driver.findElement(By.xpath(categoryXpath)).getText();

			if (categoryValue.equals("Furniture")) {
				String price = driver.findElement(By.xpath(priceXpath)).getText();
				System.out.println(price);
			}
		}

		//10. Bir method olusturun, Test sayfasindan satir ve sutun sayisini girdigimde
		//bana datayi yazdirsin
		System.out.println("=========");
		getData(1, 4);
		getData(2, 2);
		getData(3, 4);
		getData(5, 3);
	}
	public void getData(int row, int col) {
		String dynamicXpath = "//div[@role='trow']["+ row +"]/div[@role='tdata'][" + col + "]";

		WebElement cellData = driver.findElement(By.xpath(dynamicXpath));

		System.out.println(cellData.getText());
	}
}
