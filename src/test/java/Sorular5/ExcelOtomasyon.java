package Sorular5;

import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelOtomasyon {

	@Test
	public void test01() throws IOException {
		//7. Dosya yolunu bir String degiskene atayalim
		String filePath = "src/test/java/Sorular5/ulkeler.xlsx";

		//8. FileInputStream objesi olusturup,parametre olarak dosya yolunu girelim
		FileInputStream fis = new FileInputStream(filePath);

		//9. Workbook objesi olusturalim,parameter olarak fileInputStream objesini girelim
		//10. WorkbookFactory.create(fileInputStream)
		Workbook workbook = WorkbookFactory.create(fis);

		//11. Worksheet objesi olusturun workbook.getSheetAt(index)
		Sheet sayfa1 = workbook.getSheet("Sayfa1");

		//12. Row objesi olusturun sheet.getRow(index)
		Row row1 = sayfa1.getRow(1);

		//13. Cell objesi olusturun row.getCell(index)
		Cell cell1 = row1.getCell(1);
	}
}
