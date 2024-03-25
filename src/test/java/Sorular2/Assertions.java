package Sorular2;

import org.junit.Assert;
import org.junit.Test;

public class Assertions {

	@Test
	public void assertTest() {
		String expectedText = "hello";
		String actualText = "merhaba, nasılsın iyi misin hello güle güle!!!!!^+%^+%^+%";

		//Metinde hello geçtiğini test edin

		Assert.assertTrue(actualText.contains(expectedText));

		//System.out.println("test fail olursa burası çalışmaz");

		//metinde abcdef geçmediğini test edin

		String unexpectedText = "abcdef";
		Assert.assertFalse(actualText.contains(unexpectedText));

		int i = 10;
		int j = 11;
		Assert.assertNotEquals(i, j);

		//Assert.assertEquals(i, j);

		double x = 3.200000000000;
		double y = 3.200000000001;
		Assert.assertEquals(x, y, 0.0001);

		Assert.assertEquals(1, 4, 3);
		/*if (actualText.contains(expectedText)) {
			System.out.println("Test Passed!");
		}
		else
			System.out.println("Test Failed");
		 */

		Assert.assertEquals("Hello", "Hello");

		String expected = "aaaaerhaba";
		String actual = "Merhaba";
		Assert.assertNotEquals(expected, actual);
	}
}
