package by.training.carrent.util;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CodeGeneratorTest {
	CodeGenerator codeGenerator;
	String REGEX_CODE = "^\\d{4}$";
	
	@BeforeClass
	public void beforeClass() {
		codeGenerator = CodeGenerator.getInstance(); 
	}

	@Test
	public void generateCodeToRegistrationTest() {
		String actualCode = codeGenerator.generateCodeToRegistration();
		Assert.assertTrue(actualCode.matches(REGEX_CODE));
	}

	@AfterClass
	public void afterClass() {
		codeGenerator = null;
		REGEX_CODE = null;
	}
}
