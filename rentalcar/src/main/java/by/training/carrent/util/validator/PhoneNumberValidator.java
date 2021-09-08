package by.training.carrent.util.validator;

public enum PhoneNumberValidator {
	INSTANCE;

	private static final String PHONE_NUMBER_REGEX = "^[25|44|33|29]\\d{8}";

	public boolean isValid(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			return false;
		}
		return phoneNumber.matches(PHONE_NUMBER_REGEX);
	}

}
