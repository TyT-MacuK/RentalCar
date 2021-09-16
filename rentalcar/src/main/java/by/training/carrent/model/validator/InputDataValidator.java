package by.training.carrent.model.validator;

import org.apache.commons.validator.routines.EmailValidator;

public enum InputDataValidator {
	INSTANCE;
	
	private static final String PASSWORD_REGEX = ".{5,64}";
	private static final String NAME_REGEX = "[a-zA-Z]*|[ЁёА-я]*";
	private static final int MAX_LENGTH = 20;
	private static final String PHONE_NUMBER_REGEX = "^[25|44|33|29]\\d{8}";

	public boolean isEmailValid(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}
	
	public boolean isPasswordValid(String password) {
		if (password == null || password.isBlank()) {
			return false;
		}
		return password.matches(PASSWORD_REGEX);
	}
	
	public boolean isNameValid(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}
		return name.length() <= MAX_LENGTH && name.matches(NAME_REGEX);
	}
	
	public boolean isPhoneNumberValid(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			return false;
		}
		return phoneNumber.matches(PHONE_NUMBER_REGEX);
	}

}
