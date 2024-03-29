package Sorular4;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTest {

	@Test
	public void fileExistTest() throws IOException {
		// deneme.txt dosyasının Sorular4 package'ın içerisinde olduğunu dinamik
		// olarak test edin
		//C:\Users\avsel\IdeaProjects\JUnit_SoruCozumu\src\test\java\Sorular4\deneme.txt
		String dinamikPath = System.getProperty("user.dir") + "/src\\test\\java\\Sorular4\\deneme.txt";

		Assert.assertTrue(Files.exists(Paths.get(dinamikPath)));
		//System.out.println(Files.lines(Paths.get(dinamikPath)));
		File file = new File(dinamikPath);

		Assert.assertTrue(file.exists());
	}
}
