package by.training.carrent.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterCodeGenerator.
 */
public class RegisterCodeGenerator {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The Constant INSTANCE. */
	private static final RegisterCodeGenerator INSTANCE = new RegisterCodeGenerator();
	
	/** The Constant SIZE_4. */
	private static final int SIZE_4 = 10000;
	
	/**
	 * Instantiates a new register code generator.
	 */
	private RegisterCodeGenerator() {
	}
	
	/**
	 * Gets the single instance of RegisterCodeGenerator.
	 *
	 * @return single instance of RegisterCodeGenerator
	 */
	public static RegisterCodeGenerator getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Generate code.
	 *
	 * @return the string
	 */
	public String generateCode() {
		logger.log(Level.INFO, "method generateCode()");
		int code = (int) (Math.random() * SIZE_4);
		return String.valueOf(code);
	}

}
