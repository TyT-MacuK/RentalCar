package by.training.carrent.util.validator;

public enum NameValidator {
	INSTANCE;

	private static final String NAME_REGEX = "[a-zA-Z]*|[ЁёА-я]*";
	private static final int MAX_LENGTH = 20;

	public boolean isValid(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}
		return name.length() <= MAX_LENGTH && name.matches(NAME_REGEX);
	}

}
