package Sorular4;

import com.github.javafaker.Faker;

public class FakerLorem {
	public static void main(String[] args) {
		Faker faker = new Faker();
		System.out.println(faker.lorem().paragraph(2));
		System.out.println(faker.business().creditCardExpiry());
		System.out.println(faker.number().numberBetween(11, 12));
		System.out.println(faker.internet().ipV4Address());
	}
}
