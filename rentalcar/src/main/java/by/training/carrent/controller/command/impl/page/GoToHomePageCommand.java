package by.training.carrent.controller.command.impl.page;

import java.util.ArrayList;
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
import by.training.carrent.model.service.impl.CarServiceImpl;

import static by.training.carrent.controller.command.SessionAttribute.*;

public class GoToHomePageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String ENGLISH = "en";
	private static final int LIMIT_CARS_ON_PAGE = 3;

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(PREVIOUS_PAGE, PagePath.HOME_PAGE_REDIRECT);
		if (session.getAttribute(LOCALE) == null) {
			session.setAttribute(LOCALE, ENGLISH);
		}
		if (session.getAttribute(IS_AUTHENTICATED) == null) {
			session.setAttribute(IS_AUTHENTICATED, false);
		}
		String page = request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER);
		double currentPageNumber;
		if (page != null) {
			currentPageNumber = Integer.parseInt(page);
		} else {
			currentPageNumber = 1;
		}
		int leftBorderCars = (int) (LIMIT_CARS_ON_PAGE * (currentPageNumber - 1));
		CarServiceImpl service = CarServiceImpl.getInstance();
		List<Car> cars = new ArrayList<>();
		try {
			double numberOfCars = service.countCars();
			double maxNumberOfPages = Math.ceil(numberOfCars / LIMIT_CARS_ON_PAGE);
			cars = service.findByLimit(leftBorderCars, LIMIT_CARS_ON_PAGE);
			session.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
			session.setAttribute(MAX_NUMBER_OF_PAGES, maxNumberOfPages);
			router = new Router(PagePath.HOME_PAGE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on home page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		request.setAttribute("cars", cars);
		logger.log(Level.INFO, "redirected to home page");
		return router;
	}

}
