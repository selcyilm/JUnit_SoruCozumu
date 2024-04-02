package Sorular5;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelOtomasyon3 {

	@Test
	public void test01() throws IOException {
		String filePath = "src/test/java/Sorular5/ulkeler.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);

		//3) Adimlari takip ederek Sayfa1’deki 1.satira kadar gidelim
		Sheet sayfa1 = workbook.getSheet("Sayfa1");
		Row row1 = sayfa1.getRow(0);

		//4) 5.hucreye yeni bir cell olusturalim
		row1.createCell(4);

		//5) Olusturdugumuz hucreye “Nufus” yazdiralim
		row1.getCell(4).setCellValue("Nufus");

		//6) 2.satir nufus kolonuna 1500000 yazdiralim
		Row row2 = sayfa1.getRow(1);
		row2.createCell(4).setCellValue(1_500_000);

		//7) 10.satir nufus kolonuna 250000 yazdiralim
		Row row10 = sayfa1.getRow(9);
		row10.createCell(4).setCellValue(250_000);

		//8) 15.satir nufus kolonuna 54000 yazdiralim
		Row row15 = sayfa1.getRow(14);
		row15.createCell(4).setCellValue(54_000);

		//9) Dosyayi kaydedelim
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);

		//10)Dosyayi kapatalim
		fis.close();
		workbook.close();
	}
}
