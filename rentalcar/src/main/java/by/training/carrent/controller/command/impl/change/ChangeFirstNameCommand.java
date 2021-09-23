package by.training.carrent.controller.command.impl.change;

import static by.training.carrent.controller.command.SessionAttribute.USER;

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
import by.training.carrent.model.service.impl.UserServiceImpl;
import by.training.carrent.model.validator.InputDataValidator;

public class ChangeFirstNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		String name = request.getParameter(RequestParameter.USER_FIRST_NAME);
		UserServiceImpl service = UserServiceImpl.getInstance();
		InputDataValidator validator = InputDataValidator.getInstance();
		if (validator.isNameValid(name)) {
			try {
				service.updateFirstName(user.getUserId(), name);
				user.setFirstName(name);
				session.setAttribute(USER, user);
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
				logger.log(Level.INFO, "the name was changed successfully");
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during changing user name: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			logger.log(Level.INFO, "entered data is incorrect");
			router = new Router(PagePath.CHANGE_FIRST_NAME_PAGE);
			request.setAttribute(RequestParameter.CHANGE_ERROR, true);
		}
		return router;
	}

}
