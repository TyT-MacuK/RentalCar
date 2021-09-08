package by.training.carrent.util.validator;

import org.apache.commons.validator.routines.EmailValidator;

public enum CustomEmailValidator {
	INSTANCE;

	public boolean isValid(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}

}
