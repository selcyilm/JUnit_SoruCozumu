package Utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ReusableMethods {

	public static void wait(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			System.out.println("Bekleme sirasinda hata olustu!");
		}
	}

	public static List<String> dropDownStringList(List<WebElement> webElementList) {
		List<String> dondurulenListe = new ArrayList<>();

		for (WebElement each : webElementList) {
			dondurulenListe.add(each.getText());
		}
		return (dondurulenListe);
	}

	public static void dropDownYazdırma(List<WebElement> webElementList) {
		int i = 0;

		for (WebElement each : webElementList) {
			System.out.println("Ay " + i + ": " + each.getText());
			i++;
		}
	}
}
