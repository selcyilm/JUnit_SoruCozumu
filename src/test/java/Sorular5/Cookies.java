package Sorular5;

import Utils.ReusableMethods;
import Utils.TestBase_BeforeAfter;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

public class Cookies extends TestBase_BeforeAfter {

	@Test
	public void test01() {
		//1- testotomasyonu anasayfaya gidin
		driver.get("https://www.testotomasyonu.com");
		ReusableMethods.wait(1);
		//2- tum cookie’leri listeleyin
		//System.out.println(driver.manage().getCookies());
		Set<Cookie> sayfaCookies = driver.manage().getCookies();

		System.out.println(sayfaCookies.size());

		for (Cookie each : sayfaCookies) {
			System.out.println(each);
		}

		//3- Sayfadaki cookies sayisinin 5’den olmadığını test edin
		int expectedSize = 5;
		int actualSize = sayfaCookies.size();

		Assert.assertFalse(actualSize > 5);

		//4- ismi test_otomasyonu_session olan cookie expired date'inin "2024" içerdiğini test edin
		//System.out.println(driver.manage().getCookieNamed("test_otomasyonu_session").getValue());
		Cookie testOtomasyonuSession = driver.manage().getCookieNamed("test_otomasyonu_session");

		System.out.println(testOtomasyonuSession.getExpiry());
		String expectedYear = "2024";
		String actualYear = testOtomasyonuSession.getExpiry().toString();

		Assert.assertTrue(actualYear.contains(expectedYear));

		//5- ismi “en sevdigim cookie” ve degeri “cikolatali” olan bir cookie
		//olusturun ve sayfaya ekleyin
		Cookie enSevdigimCookie = new Cookie("en sevdigim cookie","cikolatali");
		driver.manage().addCookie(enSevdigimCookie);

		//6- eklediginiz cookie’nin sayfaya eklendigini test edin
		sayfaCookies = driver.manage().getCookies();

		Assert.assertTrue(sayfaCookies.contains(enSevdigimCookie));

		//7- ismi test_otomasyonu_session olan cookie’yi silin ve silindigini test edin
		driver.manage().deleteCookieNamed("test_otomasyonu_session");
		sayfaCookies = driver.manage().getCookies();
		boolean isThereCookie = false;

		for (Cookie each : sayfaCookies) {
			if (each.getName().equals("test_otomasyonu_session")) {
				isThereCookie = true;
				break ;
			}
		}

		Assert.assertFalse(isThereCookie);

		//8- tum cookie’leri silin ve silindigini test edin
		driver.manage().deleteAllCookies();

		sayfaCookies = driver.manage().getCookies();

		Assert.assertEquals(0, sayfaCookies.size());
	}
}
