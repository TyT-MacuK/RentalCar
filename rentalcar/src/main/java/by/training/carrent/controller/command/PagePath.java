package by.training.carrent.controller.command;

public final class PagePath {
	public static final String SIGN_UP_PAGE = "/pages/sign_up.jsp";
	public static final String SIGN_UP_PAGE_REDIRECT = "/rentalcar/controller?command=to_sign_up_page_command";
	
	public static final String CODE_PAGE = "/pages/code.jsp";
	public static final String PAYMENT_PAGE = "/pages/payment.jsp";
	
	public static final String HOME_PAGE = "/pages/home.jsp";
	public static final String HOME_PAGE_REDIRECT = "/rentalcar/controller?command=to_home_page_command";
	
	public static final String SIGN_IN_PAGE = "/pages/sign_in.jsp";
	public static final String SIGN_IN_PAGE_REDIRECT = "/rentalcar/controller?command=to_sign_in_page_command";
	
	public static final String PERSONAL_PROFILE_REDIRECT = "/rentalcar/controller?command=to_personal_profile_page_command";
	public static final String PERSONAL_PROFILE_PAGE = "/pages/personal_profile.jsp";
	
	public static final String MAKE_ORDER_REDIRECT = "/rentalcar/controller?command=to_make_order_page_command";
	public static final String MAKE_ORDER_PAGE = "/pages/make_order.jsp";
	
	public static final String CHANGE_FIRST_NAME_REDIRECT = "/rentalcar/controller?command=to_change_first_name_page_command";
	public static final String CHANGE_FIRST_NAME_PAGE = "/pages/change_first_name.jsp";
	public static final String CHANGE_LAST_NAME_REDIRECT = "/rentalcar/controller?command=to_change_last_name_page_command";
	public static final String CHANGE_LAST_NAME_PAGE = "/pages/change_last_name.jsp";
	public static final String CHANGE_PHONE_NUMBER_REDIRECT = "/rentalcar/controller?command=to_change_phone_number_page_command";
	public static final String CHANGE_PHONE_NUMBER_PAGE = "/pages/change_phone_number.jsp";
	public static final String CHANGE_EMAIL_REDIRECT = "/rentalcar/controller?command=to_change_email_page_command";
	public static final String CHANGE_EMAIL_PAGE = "/pages/change_email.jsp";
	public static final String CHANGE_PASSWORD_REDIRECT = "/rentalcar/controller?command=to_change_password_page_command";
	public static final String CHANGE_PASSWORD_PAGE = "/pages/change_password.jsp";
	
	public static final String ERROR_500_PAGE = "pages/error/error500.jsp";
	
	public static final String ERROR_403_PAGE = "pages/error/error403.jsp";
	
	public static final String ERROR_404_PAGE = "pages/error/error404.jsp";
	public static final String ERROR_404_PAGE_REDIRECT = "/rentalcar/controller?command=to_error_page";
	
	private PagePath() {
	}
}
