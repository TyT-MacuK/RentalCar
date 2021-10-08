package by.training.carrent.controller.command.impl.page.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;

import static by.training.carrent.controller.command.SessionAttribute.*;

import java.util.List;

public class GoToAdminUsersPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final int LIMIT_ORDERS_ON_PAGE = 3;
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER);
		if (user.getRole() != User.UserRole.ADMIN) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		Router router;
		session.setAttribute(PREVIOUS_PAGE, PagePath.ADMIN_USERS_REDIRECT);
		String page = request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER);
		int currentPageNumber;
		if (page != null) {
			currentPageNumber = Integer.parseInt(page);
		} else {
			currentPageNumber = 1;
		}
		UserServiceImpl service = UserServiceImpl.getInstance();
		try {
			double numberOfUsers = service.countUsers();
			double maxNumberOfPages = Math.ceil(numberOfUsers / LIMIT_ORDERS_ON_PAGE);
			int leftBorderUsers = (LIMIT_ORDERS_ON_PAGE * (currentPageNumber - 1));
			List<User> users = service.findByLimit(leftBorderUsers, LIMIT_ORDERS_ON_PAGE);
			session.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
			session.setAttribute(MAX_NUMBER_OF_PAGES, maxNumberOfPages);
			session.setAttribute(RequestParameter.LIST_USERS, users);
			router = new Router(PagePath.ADMIN_USERS_PAGE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on users page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
