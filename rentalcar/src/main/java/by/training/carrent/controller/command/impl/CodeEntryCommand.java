package by.training.carrent.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;

public class CodeEntryCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "methed execute()");
		Router router;
		UserServiceImpl service = UserServiceImpl.getInstance();
		String enteredCode = request.getParameter(RequestParameter.CODE);
		try {
			Optional<User> user = service.findByPasswordForAuthentication(enteredCode);
			if (user.isPresent()) {
				long userId = user.get().getUserId();
				service.updateStatus(userId, User.UserStatus.ACTIVE.ordinal() + 1);
				service.updatePasswordForAuthentication(userId, null);
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
				logger.log(Level.INFO, "the code is confirmed. Status changed to active");
			} else {
				logger.log(Level.ERROR, "the entered code is incorrect");
				request.setAttribute(RequestParameter.ENTERED_CODE_ERROR, true);
				router = new Router(PagePath.CODE_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error while entering code", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}