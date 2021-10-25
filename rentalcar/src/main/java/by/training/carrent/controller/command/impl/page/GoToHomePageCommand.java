package by.training.carrent.controller.command.impl.page;

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
	private static final int LIMIT_CARS_ON_PAGE = 3;

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(PREVIOUS_PAGE, PagePath.HOME_PAGE_REDIRECT);
		String page = request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER);
		int currentPageNumber = page != null ? Integer.parseInt(page) : 1;
		int leftBorderCars = (LIMIT_CARS_ON_PAGE * (currentPageNumber - 1));
		try {
			CarServiceImpl service = CarServiceImpl.getInstance();
			double numberOfCars = service.countCars();
			double maxNumberOfPages = Math.ceil(numberOfCars / LIMIT_CARS_ON_PAGE);
			List<Car> cars = service.findByLimit(leftBorderCars, LIMIT_CARS_ON_PAGE);
			session.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
			session.setAttribute(MAX_NUMBER_OF_PAGES, maxNumberOfPages);
			session.setAttribute(RequestParameter.LIST_CARS, cars);
			router = new Router(PagePath.HOME_PAGE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on home page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
