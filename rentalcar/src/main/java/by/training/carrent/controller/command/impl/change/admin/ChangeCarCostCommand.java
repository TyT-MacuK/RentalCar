package by.training.carrent.controller.command.impl.change.admin;

import java.math.BigDecimal;

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
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.CarServiceImpl;

public class ChangeCarCostCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		if (!user.getRole().equals(User.UserRole.ADMIN)) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		String carId = request.getParameter(RequestParameter.CAR_ID);
		String carCost = request.getParameter(RequestParameter.CAR_COST);
		CarServiceImpl service = CarServiceImpl.getInstance();
		if (carId != null && !carId.isBlank() && carCost != null && !carCost.isBlank()) {
			try {
				service.updateCost(Long.parseLong(carId), new BigDecimal(carCost));
				router = new Router(PagePath.ADMIN_CARS_REDIRECT);
				router.setRedirect();
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during changing car cost: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			router = new Router(PagePath.ADMIN_CARS_PAGE);
			request.setAttribute(RequestParameter.CHANGE_COST_INCORRECT, true);
		}
		return router;
	}
}
