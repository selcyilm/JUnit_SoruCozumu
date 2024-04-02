package Sorular5;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExcelOtomasyon2 {

	@Test
	public void readExcel2() throws Exception {
		String filePath = "src/test/java/Sorular5/ulkeler.xlsx";

		FileInputStream fis = new FileInputStream(filePath);

		Workbook workbook = WorkbookFactory.create(fis);

		Sheet sayfa1 = workbook.getSheet("Sayfa1");

		//1.satirdaki 2.hucreye gidelim ve yazdiralim
		System.out.println(sayfa1.getRow(0).getCell(1));

		// 1.satirdaki 2.hucreyi bir string degiskene atayalim ve yazdiralim
		String row1col2 = sayfa1.getRow(0).getCell(1).toString();

		System.out.println(row1col2);

		//- 2.satir 4.cell’in afganistan’in baskenti oldugunu test edelim
		String expectedCenter = "Kabil";
		String actualCenter = sayfa1.getRow(1).getCell(3).toString();

		Assert.assertEquals(expectedCenter, actualCenter);

		//- Satir sayisini bulalim
		int satirSayisi = sayfa1.getLastRowNum();
		System.out.println(satirSayisi);

		//- Fiziki olarak kullanilan satir sayisini bulun
		int fizikiSatirSayisi = sayfa1.getPhysicalNumberOfRows();
		System.out.println(fizikiSatirSayisi);

		//Senegal'in türkçe başkent isminin Dakar olduğunu test edin
		String expectedBaskentIsim = "Dakar";
		String actualBaskentIsim = "";

		for (int i = 0; i <= sayfa1.getLastRowNum(); i++) {
			String ulkeIsmi = sayfa1.getRow(i).getCell(0).toString();

			if (ulkeIsmi.equals("Senegal")) {
				actualBaskentIsim = sayfa1.getRow(i).getCell(3).toString();
				break ;
			}
		}
		Assert.assertEquals(expectedBaskentIsim, actualBaskentIsim);

		//ülkeler excel'inde türkçe baskenti Berlin olan bir ülke olduğunu test edin
		boolean flag = false;

		for (int i = 0; i <= sayfa1.getLastRowNum(); i++) {
			String baskent = sayfa1.getRow(i).getCell(3).toString();

			if (baskent.equals("Berlin")) {
				flag = true;
				break ;
			}
		}

		Assert.assertTrue(flag);

		//- Turkçe Ulke isimleri ve baskentleri bir map olarak kaydedelim
		Map<String, String> ulkelerMap = new HashMap<>();

		for (int i = 0; i <= sayfa1.getLastRowNum(); i++) {
			String ulkeIsim = sayfa1.getRow(i).getCell(2).toString();
			String ulkeBaskent = sayfa1.getRow(i).getCell(3).toString();

			ulkelerMap.put(ulkeIsim, ulkeBaskent);
		}

		//ülkeler excel'inde türkçe baskent isimleri B ile başlayan ulkeleri yazdıralım

		Set<String> ulkeIsimleriSet = ulkelerMap.keySet();

		System.out.println(ulkeIsimleriSet);

		for (String eachUlkeIsmi : ulkeIsimleriSet) {
			String eachBaskent = ulkelerMap.get(eachUlkeIsmi);

			if (eachBaskent.startsWith("B")) {
				System.out.println("Ulke: " + eachUlkeIsmi + "Baskent: " + eachBaskent);
			}
		}

		//ülkeler excel'inde türkçe baskenti Berlin olan bir ülke olduğunu test edin
		boolean berlin = ulkelerMap.containsValue("Berlin");

		Assert.assertTrue(berlin);

		fis.close();
		workbook.close();
	}
}
