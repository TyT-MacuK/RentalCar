package by.training.carrent.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.training.carrent.exception.ServiceException;

public class EmailSenderTest {
	EmailSender emailSender;

	@BeforeClass
	public void initialize() {
		emailSender = EmailSender.getInstance();
	}

	@DataProvider()
	public Object[][] createData() {
		return new Object[][] { { "korozarecipient@gmail.com", true }, { "", false }, { " ", false }, { null, false } };
	}

	@Test(description = "Testing method sendMail", dataProvider = "createData")
	public void sendMailTest(String recipient, boolean expected) throws ServiceException {
		String code = "TEST";
		boolean actual = emailSender.sendMail(recipient, code);
		assertEquals(actual, expected);
	}

	@AfterClass
	public void tierDown() {
		emailSender = null;
	}
}
