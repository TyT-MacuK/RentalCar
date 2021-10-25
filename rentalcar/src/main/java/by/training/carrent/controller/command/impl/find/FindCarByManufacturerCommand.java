package by.training.carrent.controller.command.impl.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

public class FindCarByManufacturerCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		String manufacturer = request.getParameter(RequestParameter.CAR_MANUFACTURER);
		if (manufacturer != null && !manufacturer.isBlank()) {
			CarServiceImpl service = CarServiceImpl.getInstance();
			try {
				List<Car> cars = service.findByManufacture(manufacturer);
				if (cars.isEmpty()) {
					request.setAttribute(RequestParameter.LIST_IS_EMPTY, true);
				}
				request.setAttribute(RequestParameter.LIST_CARS, cars);
				router = new Router(PagePath.HOME_PAGE);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during find cars by manufacturer: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			request.setAttribute(RequestParameter.CAR_MANUFACTURER_INCORRECT, true);
			return new Router(PagePath.HOME_PAGE);
		}
		return router;
	}
}
