package by.training.carrent.model.validator;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * The Class InputDataValidator.
 */
public class InputDataValidator {

	/** The Constant instance. */
	private static final InputDataValidator instance = new InputDataValidator();

	/** The Constant PASSWORD_REGEX. */
	private static final String PASSWORD_REGEX = "^[^<>]{5,64}$";

	/** The Constant NAME_REGEX. */
	private static final String NAME_REGEX = "[a-zA-Z]+|[а-яА-ЯёЁ]+";

	/** The Constant MAX_LENGTH. */
	private static final int MAX_LENGTH_NAME = 20;

	/** The Constant PHONE_NUMBER_REGEX. */
	private static final String PHONE_NUMBER_REGEX = "^[25|44|33|29]\\d{8}";

	/** The Constant CARD_NUMBER_REGEX. */
	private static final String CARD_NUMBER_REGEX = "^\\d{16}$";

	/** The Constant CVV_REGEX. */
	private static final String CVV_REGEX = "^\\d{3}$";

	/** The Constant CAR_MODEL_REGEX. */
	private static final String CAR_MODEL_REGEX = "^[\\w{1,50}\\s]+$";

	/** The Constant CAR_YEAR_REGEX. */
	private static final String CAR_YEAR_REGEX = "^\\d{4}$";

	/** The Constant CAR_COST_REGEX. */
	private static final String CAR_COST_REGEX = "^[\\d]*[.,]?[\\d]+$";

	/** The Constant CAR_DISCOUNT_REGEX. */
	private static final String CAR_DISCOUNT_REGEX = "^\\d{1,2}$";

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
	 * Checks if is the user data valid.
	 *
	 * @param email the email
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param phoneNumber the phone number
	 * @return true, if is the user data valid
	 */
	public boolean isUserValid(String email, String password, String firstName, String lastName, String phoneNumber) {
		return isEmailValid(email) && isPasswordValid(password) && isNameValid(firstName) && isNameValid(lastName)
				&& isPhoneNumberValid(phoneNumber);
	}

	/**
	 * Checks if email is valid.
	 *
	 * @param email the email
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
	 * @param password the password
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
	 * @param name the name
	 * @return true, if name is valid
	 */
	public boolean isNameValid(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}
		return name.length() <= MAX_LENGTH_NAME && name.matches(NAME_REGEX);
	}

	/**
	 * Checks if phone number is valid.
	 *
	 * @param phoneNumber the phone number
	 * @return true, if phone number is valid
	 */
	public boolean isPhoneNumberValid(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			return false;
		}
		return phoneNumber.matches(PHONE_NUMBER_REGEX);
	}

	/**
	 * Checks if is card number valid.
	 *
	 * @param cardNumber the card number
	 * @return true, if is card number valid
	 */
	public boolean isCardNumberValid(String cardNumber) {
		if (cardNumber == null || cardNumber.isBlank()) {
			return false;
		}
		return cardNumber.matches(CARD_NUMBER_REGEX);
	}

	/**
	 * Checks if is cvv valid.
	 *
	 * @param cvv the cvv
	 * @return true, if is cvv valid
	 */
	public boolean isCvvValid(String cvv) {
		if (cvv == null || cvv.isBlank()) {
			return false;
		}
		return cvv.matches(CVV_REGEX);
	}

	/**
	 * Checks if is car data valid.
	 *
	 * @param model    the model
	 * @param year     the year
	 * @param cost     the cost
	 * @param discount the discount
	 * @return true, if is car data valid
	 */
	public boolean isCarDataValid(String model, String year, String cost, String discount) {
		return isCarModelValid(model) && isCarYearValid(year) && isCarCostValid(cost) && isCarDiscountValid(discount);
	}

	/**
	 * Checks if is car model valid.
	 *
	 * @param model the model
	 * @return true, if is car model valid
	 */
	private boolean isCarModelValid(String model) {
		if (model == null || model.isBlank()) {
			return false;
		}
		return model.matches(CAR_MODEL_REGEX);
	}

	/**
	 * Checks if is car year valid.
	 *
	 * @param year the year
	 * @return true, if is car year valid
	 */
	private boolean isCarYearValid(String year) {
		if (year == null || year.isBlank()) {
			return false;
		}
		return year.matches(CAR_YEAR_REGEX);
	}

	/**
	 * Checks if is car cost valid.
	 *
	 * @param cost the cost
	 * @return true, if is car cost valid
	 */
	private boolean isCarCostValid(String cost) {
		if (cost == null || cost.isBlank()) {
			return false;
		}
		return cost.matches(CAR_COST_REGEX);
	}

	/**
	 * Checks if is car discount valid.
	 *
	 * @param discount the discount
	 * @return true, if is car discount valid
	 */
	private boolean isCarDiscountValid(String discount) {
		if (discount == null || discount.isBlank()) {
			return false;
		}
		return discount.matches(CAR_DISCOUNT_REGEX);
	}
}
