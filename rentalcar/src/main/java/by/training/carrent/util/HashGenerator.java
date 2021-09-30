package by.training.carrent.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class HashGenerator.
 */
public class HashGenerator {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The Constant INSTANCE. */
	private static final HashGenerator INSTANCE = new HashGenerator();
	
	/** The Constant ALGORITHM_SHA256. */
	private static final String ALGORITHM_SHA256 = "SHA-256";
	
	/** The Constant HEXADECIMAL_FORMAT. */
	private static final String HEXADECIMAL_FORMAT = "%02x";

	/**
	 * Instantiates a new hash generator.
	 */
	private HashGenerator() {
	}
	
	/**
	 * Gets the single instance of HashGenerator.
	 *
	 * @return single instance of HashGenerator
	 */
	public static HashGenerator getInstance() {
		return INSTANCE;
	}

	/**
	 * Generate password hash.
	 *
	 * @param password
	 * @return string
	 */
	public String generatePasswordHash(String password) {
		logger.log(Level.INFO, "method generatePasswordHash()");
		byte[] passwordBytes = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA256);
			messageDigest.update(password.getBytes());
			passwordBytes = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.ERROR, "Algorithm is not found", e);
		}
		String result = conventBytesToString(passwordBytes);
		logger.log(Level.INFO, "password has been hashed");
		return result;
	}

	/**
	 * Convent bytes to string.
	 *
	 * @param passwordBytes
	 * @return string
	 */
	private static String conventBytesToString(byte[] passwordBytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : passwordBytes) {
			builder.append(String.format(HEXADECIMAL_FORMAT, b));
		}
		return builder.toString();
	}
}
