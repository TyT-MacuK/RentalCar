package by.training.carrent.controller.command;

public final class RequestParameter {
	public static final String COMMAND = "command";
	public static final String CURRENT_PAGE_NUMBER = "page";
	public static final String CAR_BOOKED = "booked";

	// Sign up confirmation code markers
	public static final String CODE = "code";

	// User data markers
	public static final String USER_ID = "id";
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_DATE_OF_BIRTH = "date_of_birth";
	public static final String USER_PHONE_NUMBER = "phone_number";
	public static final String USER_DISCOUNT = "user_discount";
	public static final String USER_ROLE = "role";
	public static final String USER_STATUS = "status";
	public static final String USER_PASSWORD_FOR_AUTHENTICATION = "password_for_authentication";

	// Password data markers
	public static final String OLD_PASSWORD = "old_password";
	public static final String NEW_PASSWORD = "new_password";

	// Date data markers
	public static final String PICK_UP_DATE = "pick_up_date";
	public static final String RETURN_DATE = "return_date";

	// Car data markers
	public static final String CAR_ID = "car_id";
	public static final String CAR_MANUFACTURER = "manufacturer";
	public static final String CAR_MODEL = "model";
	public static final String CAR_YEAR = "year";
	public static final String CAR_CONDITIONER = "conditioner";
	public static final String CAR_TRANSMISSION = "transmission";
	public static final String CAR_COST = "cost";
	public static final String CAR_DISCOUNT = "car_discount";
	public static final String CAR_STATUS = "car_status";

	// List data markers
	public static final String LIST_ORDERS = "orders";
	public static final String LIST_CARS = "cars";
	public static final String LIST_USERS = "users";

	// Order data markers
	public static final String ORDER_ID = "order_id";

	// Image data markers
	public static final String IMAGE = "image";
	public static final String PART = "part";
	public static final String IMAGE_UPLOAD_DIRECTORY = "image_upload_directory";

	// Incorrect data markers
	public static final String CHANGE_CARS_STATUS_INCORRECT = "change_car_status_incorrect";
	public static final String ORDER_INCORRECT_DATE = "incorrect_date";
	public static final String ORDER_PICK_UP_BEFORE_RETURN = "pick_up_date_is_before_return_date";
	public static final String INVALID_FILE = "invalid_file";
	public static final String INPUT_DATA_INCORRECT = "input_data_incorrect";
	public static final String ORDER_ID_INCORRECT = "order_id_incorrect";
	public static final String CAR_MANUFACTURER_INCORRECT = "car_manufacturer_incorrect";

	// Incorrect data to changes markers
	public static final String CHANGE_DISCOUNT_INCORRECT = "change_discount_incorrect";
	public static final String CHANGE_STATUS_INCORRECT = "change_status_incorrect";
	public static final String CHANGE_ROLE_INCORRECT = "change_role_incorrect";
	public static final String CHANGE_COST_INCORRECT = "change_cost_incorrect";
	public static final String CHANGE_ORDERS_STATUS_INCORRECT = "change_order_status_incorrect";

	// Error data markers
	public static final String AUTHENTICATION_ERROR = "authentication_error";
	public static final String CHANGE_ERROR = "change_error";
	public static final String ENTERED_CODE_ERROR = "entered_code_error";
	public static final String LIST_IS_EMPTY = "list_empty";
	public static final String NOT_ENOUGHT_MONEY_TO_PAY = "not_enough_money";

	private RequestParameter() {
	}

}
