package by.training.carrent.model.validator;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * The Class InputDataValidator.
 */
public class InputDataValidator {
	
	/** The Constant instance. */
	private static final InputDataValidator instance = new InputDataValidator();
	
	/** The Constant PASSWORD_REGEX. */
	private static final String PASSWORD_REGEX = ".{5,64}";
	
	/** The Constant NAME_REGEX. */
	private static final String NAME_REGEX = "[a-zA-Z]*|[ЁёА-я]*";
	
	/** The Constant MAX_LENGTH. */
	private static final int MAX_LENGTH = 20;
	
	/** The Constant PHONE_NUMBER_REGEX. */
	private static final String PHONE_NUMBER_REGEX = "^[25|44|33|29]\\d{8}";
	
	/**
	 * Instantiates a new input data validator.
	 */
	private InputDataValidator() {
	}
	
	/**
	 * Gets the single instance of InputDataValidator.
	 *
	 * @return single instance of InputDataValidator
	 */
	public static InputDataValidator getInstance() {
		return instance;
	}

	/**
	 * Checks if email is valid.
	 *
	 * @param  email
	 * @return true, if is email valid
	 */
	public boolean isEmailValid(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}
	
	/**
	 * Checks if password is valid.
	 *
	 * @param password
	 * @return true, if password is valid
	 */
	public boolean isPasswordValid(String password) {
		if (password == null || password.isBlank()) {
			return false;
		}
		return password.matches(PASSWORD_REGEX);
	}
	
	/**
	 * Checks if name is valid.
	 *
	 * @param  name
	 * @return true, if name is valid
	 */
	public boolean isNameValid(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}
		return name.length() <= MAX_LENGTH && name.matches(NAME_REGEX);
	}
	
	/**
	 * Checks if phone number is valid.
	 *
	 * @param phoneNumber
	 * @return true, if phone number is valid
	 */
	public boolean isPhoneNumberValid(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			return false;
		}
		return phoneNumber.matches(PHONE_NUMBER_REGEX);
	}

}
