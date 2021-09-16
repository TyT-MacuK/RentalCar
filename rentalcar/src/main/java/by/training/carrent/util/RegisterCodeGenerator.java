package by.training.carrent.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCodeGenerator {
	private static final Logger logger = LogManager.getLogger();
	private static final RegisterCodeGenerator INSTANCE = new RegisterCodeGenerator();
	private static final int SIZE_4 = 10000;
	
	private RegisterCodeGenerator() {
	}
	
	public static RegisterCodeGenerator getInstance() {
		return INSTANCE;
	}
	
	public String generateCode() {
		logger.log(Level.INFO, "method generateCode()");
		int code = (int) (Math.random() * SIZE_4);
		return String.valueOf(code);
	}

}
