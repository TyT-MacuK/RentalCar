package by.training.carrent.model.dao;

public final class ColumnName {
	// Table: users
	public static final String USER_ID = "user_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	public static final String USER_PASSWORD_FOR_AUTHENTICATION = "password_for_authentication";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_DISCOUNT = "discount";
	public static final String USER_PHONE_NUMBER = "phone_number";
	public static final String USER_DATE_OF_BIRTH = "date_of_birth";
	public static final String USER_STATUS = "user_ststus_id";
	public static final String USER_ROLE = "user_role_id";
	public static final String COUNT_USERS = "count_users";

	// Table: user_status
	public static final String USER_STATUS_ID = "user_status_id";
	public static final String USER_STATUS_NAME = "user_status";

	// Table: user_role
	public static final String USER_ROLE_ID = "role_id";
	public static final String USER_ROLE_NAME = "role";

	// Table: cars
	public static final String CAR_ID = "car_id";
	public static final String CAR_MODEL = "model";
	public static final String CAR_DISCOUNT = "discount";
	public static final String CAR_YEAR = "year";
	public static final String CAR_CONDITIONER = "conditioner";
	public static final String CAR_COST = "cost";
	public static final String CAR_IMAGE_URI = "image_url";
	public static final String CAR_TRANSMISSION = "car_transmission_id";
	public static final String CAR_MANUFACTURER = "car_manufacturer_id";
	public static final String CAR_STATUS = "car_status_id";
	public static final String COUNT_CARS = "count_cars";

	// Table: car_manufacturer
	public static final String CAR_MANUFACTURER_ID = "manufacturer_id";
	public static final String CAR_MANUFACTURER_NAME = "manufacturer";

	// Table: car_status
	public static final String CAR_STATUS_ID = "car_status_id";
	public static final String CAR_STATUS_NAME = "car_status";

	// Table: orders
	public static final String ORDER_ID = "order_id";
	public static final String ORDER_PRICE = "price";
	public static final String ORDER_PICK_UP_DATE = "pick_up_date";
	public static final String ORDER_RETURN_DATE = "return_date";
	public static final String ORDER_STATUS = "order_status_id";
	public static final String ORDER_CAR = "car_id";
	public static final String ORDER_USER = "user_id";
	public static final String COUNT_ORDERS = "count_orders";

	// Table: order_status
	public static final String ORDER_STATUS_ID = "order_status_id";
	public static final String ORDER_STATUS_NAME = "order_status";

	// Table: payment_card
	public static final String PAYMENT_CARD_BALANCE = "balance";

	private ColumnName() {
	}
}
