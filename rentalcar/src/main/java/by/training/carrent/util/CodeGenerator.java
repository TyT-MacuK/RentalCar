package by.training.carrent.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class RegisterCodeGenerator.
 */
public class CodeGenerator {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The Constant INSTANCE. */
	private static final CodeGenerator INSTANCE = new CodeGenerator();
	
	/** The Constant MIN_BORDER_TO_REGISTRATION. */
	private static final int MIN_BORDER_TO_REGISTRATION = 1000;
	
	/** The Constant MAX_BORDER_TO_REGISTRATION. */
	private static final int MAX_BORDER_TO_REGISTRATION = 9999;
	
	/**
	 * Instantiates a new register code generator.
	 */
	private CodeGenerator() {
	}
	
	/**
	 * Gets the single instance of RegisterCodeGenerator.
	 *
	 * @return single instance of RegisterCodeGenerator
	 */
	public static CodeGenerator getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Generate code to registration.
	 *
	 * @return the string
	 */
	public String generateCodeToRegistration() {
		logger.log(Level.INFO, "method generateCode()");
		int code = (int) (Math.random() * (MAX_BORDER_TO_REGISTRATION - MIN_BORDER_TO_REGISTRATION)) + MIN_BORDER_TO_REGISTRATION;
		return String.valueOf(code);
	}
}
