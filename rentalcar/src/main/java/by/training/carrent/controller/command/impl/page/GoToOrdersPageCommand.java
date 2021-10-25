package by.training.carrent.controller.command.impl.page;

import static by.training.carrent.controller.command.SessionAttribute.*;

import java.util.List;
import java.util.Map;

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
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.CarServiceImpl;
import by.training.carrent.model.service.impl.OrderServiceImpl;

public class GoToOrdersPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final int LIMIT_ORDERS_ON_PAGE = 3;
	private final OrderServiceImpl service = OrderServiceImpl.getInstance();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(PREVIOUS_PAGE, PagePath.ORDERS_PAGE_REDIRECT);
		String page = request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER);
		int currentPageNumber;
		if (page != null) {
			currentPageNumber = Integer.parseInt(page);
		} else {
			currentPageNumber = 1;
		}
		int leftBorderCars = (LIMIT_ORDERS_ON_PAGE * (currentPageNumber - 1));
		User user = (User) session.getAttribute(USER);
		try {
			double numberOfOrders = service.countOrders(user.getUserId());
			double maxNumberOfPages = Math.ceil(numberOfOrders / LIMIT_ORDERS_ON_PAGE);
			List<Order> orders = service.findByUserIdAndLimit(user.getUserId(), leftBorderCars, LIMIT_ORDERS_ON_PAGE);
			session.setAttribute(CURRENT_PAGE_NUMBER, currentPageNumber);
			session.setAttribute(MAX_NUMBER_OF_PAGES, maxNumberOfPages);
			session.setAttribute(RequestParameter.LIST_ORDERS, orders);
			Map<Long, Car> carsMap = createMapCarsByUserId(user.getUserId());
			session.setAttribute(MAP_CARS, carsMap);
			router = new Router(PagePath.ORDERS_PAGE);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on orders page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}

	private Map<Long, Car> createMapCarsByUserId(long userId) throws ServiceException {
		List<Long> carsId = service.findCarsIdByUserId(userId);
		CarServiceImpl carServise = CarServiceImpl.getInstance();
		return carServise.findCarsByCarsId(carsId);
	}
}
