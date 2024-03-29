package by.training.carrent.controller.command;

public enum CommandType {
	// Common navbar command
	SIGN_IN_PAGE,
	TO_SIGN_IN_PAGE_COMMAND,
	SIGN_OUT_COMMAND,
	TO_HOME_PAGE_COMMAND,
	TO_PERSONAL_PROFILE_PAGE_COMMAND,
	
	// Sign up process
	SIGN_UP_PAGE,
	TO_SIGN_UP_PAGE_COMMAND,
	CODE_ENTRY_PAGE,
	TO_CODE_ENTRY_PAGE_COMMAND,
	
	// User parts of navbar
	TO_ORDERS_PAGE_COMMAND,
	
	// Admin parts of navbar
	TO_ADMIN_USERS_PAGE_COMMAND,
	TO_ADMIN_ORDERS_PAGE_COMMAND,
	TO_ADMIN_CARS_PAGE_COMMAND,
	
	// Make order process
	MAKE_ORDER_PAGE,
	TO_MAKE_ORDER_PAGE_COMMAND,
	PAYMENT_ENTRY_PAGE,
	TO_PAYMENT_ENTRY_PAGE_COMMAND,

	// Common find commands
	FIND_MANUFACTURER_BY_ID_COMMAND,
	
	// Change language command
	CHANGE_LANGUAGE_TO_ENGLISH_COMMAND,
	CHANGE_LANGUAGE_TO_RUSSIAN_COMMAND,
	
	// Personal profile page
	TO_CHANGE_FIRST_NAME_PAGE_COMMAND,
	CHANGE_FIRST_NAME_PAGE,
	TO_CHANGE_LAST_NAME_PAGE_COMMAND,
	CHANGE_LAST_NAME_PAGE,
	TO_CHANGE_PHONE_NUMBER_PAGE_COMMAND,
	CHANGE_PHONE_NUMBER_PAGE,
	TO_CHANGE_EMAIL_PAGE_COMMAND,
	CHANGE_EMAIL_PAGE,
	TO_CHANGE_PASSWORD_PAGE_COMMAND,
	CHANGE_PASSWORD_PAGE,
	
	// Admin page about users
	CHANGE_USER_STATUS_COMMAND,
	CHANGE_USER_ROLE_COMMAND,
	CHANGE_USER_DISCOUNT_COMMAND,
	
	// Admin page about orders
	CHANGE_ORDER_STATUS_COMMAND,
	FIND_ORDER_BY_ID_COMMAND,
	
	//Admin page about cars
	CHANGE_CAR_DISCOUNT_COMMAND,
	CHANGE_CAR_COST_COMMAND,
	CHANGE_CAR_STATUS_COMMAND,
	ADMIN_ADD_CAR_PAGE,
	TO_ADMIN_ADD_CAR_PAGE_COMMAND
}
