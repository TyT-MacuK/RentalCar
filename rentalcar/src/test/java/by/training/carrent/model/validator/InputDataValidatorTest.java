package by.training.carrent.model.validator;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InputDataValidatorTest {
	InputDataValidator validator;

	@BeforeClass
	public void initialize() {
		validator = InputDataValidator.getInstance();
	}

	@DataProvider()
	public Object[][] createDataToEmailValidator() {
		return new Object[][] { 
			{ "korozarecipient@gmail.com", true },
			{ "1@1.1", false },
			{ " ", false },
			{ null, false } };
	}
	
	@Test(description = "Testing email validator", dataProvider = "createDataToEmailValidator")
	public void isEmailValidTest(String email, boolean expected) {
		boolean actual = validator.isEmailValid(email);
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] createDataToPasswordValidator() {
		return new Object[][] {
			{"qwe11QQd!!", true},
			{"1", false},
			{" ", false},
			{null, false} };
	}

	@Test(description = "Testing password validator", dataProvider = "createDataToPasswordValidator")
	public void isPasswordValidTest(String password, boolean expected) {
		boolean actual = validator.isPasswordValid(password);
		assertEquals(actual, expected);
	}

	@DataProvider
	public Object[][] createDataToNameValidator() {
		return new Object[][] {
			{"Max", true},
			{"Валера", true},
			{" ", false},
			{null, false} };
	}
	
	@Test(description = "Testing name validator", dataProvider = "createDataToNameValidator")
	public void isNameValidTest(String name, boolean expected) {
		boolean actual = validator.isNameValid(name);
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] createDataToPhoneNumberValidator() {
		return new Object[][] {
			{"448561486", true},
			{"111111111", false},
			{"2511Q1111", false},
			{" ", false},
			{null, false} };
	}

	@Test(description = "Testing pnone number validator", dataProvider = "createDataToPhoneNumberValidator")
	public void isPhoneNumberValidTest(String password, boolean expected) {
		boolean actual = validator.isPhoneNumberValid(password);
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] createDataToCardNumberValidator() {
		return new Object[][] {
			{"1234123412341234", true},
			{"111111111", false},
			{"2511Q1111", false},
			{" ", false},
			{null, false} };
	}

	@Test(description = "Testing card number validator", dataProvider = "createDataToCardNumberValidator")
	public void isCardNumberValidTest(String cardNumber, boolean expected) {
		boolean actual = validator.isCardNumberValid(cardNumber);
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] createDataToCvvValidator() {
		return new Object[][] {
			{"123", true},
			{"111111111", false},
			{"2a1", false},
			{" ", false},
			{null, false} };
	}

	@Test(description = "Testing cvv validator", dataProvider = "createDataToCvvValidator")
	public void isCvvrValidTest(String cvv, boolean expected) {
		boolean actual = validator.isCvvValid(cvv);
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] createDataToPositiveCarValidator() {
		return new Object[][] {
			{"skoda" ,"2021", "90.36", "0"},
			{"SKODA" ,"2021", "90", "10"}};
	}
	
	@Test(description = "Testing car data validator", dataProvider = "createDataToPositiveCarValidator")
	public void positiveIsCarDataValid(String model, String year, String cost, String discount) {
		boolean actual = validator.isCarDataValid(model, year, cost, discount);
		Assert.assertTrue(actual);
	}
	
	@DataProvider
	public Object[][] createDataToNegativeCarValidator() {
		return new Object[][] {
			{"SKODA 1.6" ,"2021", "90", "10"},
			{"SKODA" ,"2021", "9a0", "10"},
			{"SKODA" ,"2021", "90", "1z0"},
			{null ,"2021", "90", "10"},
			{"SKODA" , "   ", "90", "10"},
			{"SKODA" ,"2021", null, "10"},
			{"SKODA" ,"2021-01-01", "90", " "}};
	}
	
	@Test(description = "Testing car data validator", dataProvider = "createDataToNegativeCarValidator")
	public void negativeIsCarDataValid(String model, String year, String cost, String discount) {
		boolean actual = validator.isCarDataValid(model, year, cost, discount);
		Assert.assertFalse(actual);
	}

	@AfterClass
	public void tierDown() {
		validator = null;
	}
}
