package by.training.carrent.controller.command.impl.page;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.service.impl.CarServiceImpl;

public class GoToMakeOrderPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {		
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		CarServiceImpl service = CarServiceImpl.getInstance();
		String carId = request.getParameter(RequestParameter.CAR_ID);
		session.setAttribute(SessionAttribute.PREVIOUS_PAGE,
				PagePath.MAKE_ORDER_REDIRECT + "&" + RequestParameter.CAR_ID + "=" + carId);
		try {
			Optional<Car> localCar = service.findById(Long.parseLong(carId));
			if (localCar.isPresent()) {
				Car car = localCar.get();
				router = new Router(PagePath.MAKE_ORDER_PAGE);
				session.setAttribute(SessionAttribute.CAR, car);
			} else {
				logger.log(Level.ERROR, "car is not found");
				router = new Router(PagePath.ERROR_404_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on home page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}