package by.training.carrent.controller.command;

public final class PagePath {
	public static final String SIGN_UP_PAGE = "/pages/sign_up.jsp";
	public static final String SIGN_UP_PAGE_REDIRECT = "/rentalcar/controller?command=to_sign_up_page_command";
	
	public static final String CODE_PAGE = "/pages/code.jsp";
	
	public static final String HOME_PAGE = "/pages/home.jsp";
	public static final String HOME_PAGE_REDIRECT = "/rentalcar/controller?command=to_home_page_command";
	
	public static final String ERROR_500_PAGE = "pages/error/error500.jsp";
	
	public static final String ERROR_403_PAGE = "pages/error/error403.jsp";
	
	public static final String ERROR_404_PAGE = "pages/error/error404.jsp";
	public static final String ERROR_404_PAGE_REDIRECT = "/rentalcar/controller?command=to_error_page";
	
	private PagePath() {
	}
}
