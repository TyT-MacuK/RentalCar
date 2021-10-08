package by.training.carrent.controller.command.impl.page.admin;

import static by.training.carrent.controller.command.RequestParameter.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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

public class AdminAddCarCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String STANDARD_MANUFACTURER = "choose manufacturer";
	private static final String STANDARD_TRANSMISSION = "choose transmission";
	private static final String STANDARD_STATUS = "choose status";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		if (!user.getRole().equals(User.UserRole.ADMIN)) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		Map<String, String> parameters = new HashMap<>();
		Router router;
		String manufacturer = request.getParameter(CAR_MANUFACTURER);
		String transmission = request.getParameter(CAR_TRANSMISSION);
		String status = request.getParameter(CAR_STATUS);
		if (manufacturer.equals(STANDARD_MANUFACTURER) || transmission.equals(STANDARD_TRANSMISSION)
				|| status.equals(STANDARD_STATUS)) {
			logger.log(Level.ERROR, "unknown status: {}", status);
			request.setAttribute(INPUT_DATA_INCORRECT, true);
			return new Router(PagePath.ADMIN_ADD_CAR_PAGE);
		}
		parameters.put(CAR_MANUFACTURER, manufacturer);
		parameters.put(CAR_MODEL, request.getParameter(CAR_MODEL));
		parameters.put(CAR_YEAR, request.getParameter(CAR_YEAR));
		parameters.put(CAR_CONDITIONER, request.getParameter(CAR_CONDITIONER));
		parameters.put(CAR_TRANSMISSION, transmission);
		parameters.put(CAR_COST, request.getParameter(CAR_COST));
		parameters.put(CAR_DISCOUNT, request.getParameter(CAR_DISCOUNT));
		parameters.put(CAR_STATUS, status);
		parameters.put(IMAGE_UPLOAD_DIRECTORY, (String) request.getAttribute(IMAGE_UPLOAD_DIRECTORY));
		Part part = (Part) request.getAttribute(RequestParameter.PART);
		CarServiceImpl service = CarServiceImpl.getInstance();
		try {
			service.add(parameters, part);
			router = new Router(PagePath.HOME_PAGE_REDIRECT);
		router.setRedirect();
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error on add car page: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
