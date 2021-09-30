package by.training.carrent.controller.command.impl.change;

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

public class ChangePasswordCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		UserServiceImpl service = UserServiceImpl.getInstance();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		String newPassword = request.getParameter(RequestParameter.NEW_PASSWORD);
		String oldPassword = request.getParameter(RequestParameter.OLD_PASSWORD);
		try {
			if (service.updatePassword(user.getEmail(), oldPassword, newPassword)) {
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
				logger.log(Level.INFO, "the password was changed successfully");
			} else {
				logger.log(Level.INFO, "entered data is incorrect");
				router = new Router(PagePath.CHANGE_PASSWORD_PAGE);
				request.setAttribute(RequestParameter.CHANGE_ERROR, true);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error during changing user password: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}

}
