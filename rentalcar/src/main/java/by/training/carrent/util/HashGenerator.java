package by.training.carrent.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashGenerator {
	private static final Logger logger = LogManager.getLogger();
	private static final HashGenerator INSTANCE = new HashGenerator();
	private static final String ALGORITHM_SHA256 = "SHA-256";
	private static final String HEXADECIMAL_FORMAT = "%02x";

	private HashGenerator() {
	}
	
	public static HashGenerator getInstance() {
		return INSTANCE;
	}

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

	private static String conventBytesToString(byte[] passwordBytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : passwordBytes) {
			builder.append(String.format(HEXADECIMAL_FORMAT, b));
		}
		return builder.toString();
	}
}
