package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	public static List<String> webElementTostringList(List<WebElement> webElementList) {
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

	public static void	switchWindowByUrl(WebDriver driver, String targetUrl) {

		Set<String> windowHandles = driver.getWindowHandles();

		for (String each : windowHandles) {
			driver.switchTo().window(each);

			if (driver.getCurrentUrl().equals(targetUrl)) {
				break ;
			}
		}
	}

	public static void switchWindowByTitle(WebDriver driver, String targetTitle) {
		Set<String> windowHandles = driver.getWindowHandles();

		for (String eachHandle : windowHandles) {
			driver.switchTo().window(eachHandle);

			if (driver.getTitle().equals(targetTitle)) {
				break ;
			}
		}
	}
}
