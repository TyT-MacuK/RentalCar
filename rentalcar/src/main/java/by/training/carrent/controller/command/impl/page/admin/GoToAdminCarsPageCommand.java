package by.training.carrent.controller.command.impl.page.admin;

import static by.training.carrent.controller.command.SessionAttribute.CURRENT_PAGE_NUMBER;
import static by.training.carrent.controller.command.SessionAttribute.MAX_NUMBER_OF_PAGES;
import static by.training.carrent.controller.command.SessionAttribute.PREVIOUS_PAGE;
import static by.training.carrent.controller.command.SessionAttribute.USER;

import java.util.List;

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
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.CarServiceImpl;

public class GoToAdminCarsPageCommand implements Command {
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
		session.setAttribute(PREVIOUS_PAGE, PagePath.ADMIN_CARS_REDIRECT);
		String page = request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER);
		int currentPageNumber;
		if (page != null) {
			currentPageNumber = Integer.parseInt(page);
		} else {
			currentPageNumber = 1;
		}
		CarServiceImpl service = CarServiceImpl.getInstance();
		try {
			double numberOfCars = service.countCars();
			double maxNumberOfPages = Math.ceil(numberOfCars / LIMIT_ORDERS_ON_PAGE);
			int leftBorderUsers = (LIMIT_ORDERS_ON_PAGE * (currentPageNumber - 1));
			List<Car> cars = service.findByLimit(leftBorderUsers, LIMIT_ORDERS_ON_PAGE);
			session.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
			session.setAttribute(MAX_NUMBER_OF_PAGES, maxNumberOfPages);
			session.setAttribute(RequestParameter.LIST_CARS, cars);
			router = new Router(PagePath.ADMIN_CARS_PAGE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on cars page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
