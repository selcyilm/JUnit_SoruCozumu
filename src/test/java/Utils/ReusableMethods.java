package Utils;

public class ReusableMethods {

	public static void wait(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			System.out.println("Bekleme sirasinda hata olustu!");
		}
	}
}
