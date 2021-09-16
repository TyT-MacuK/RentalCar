package by.training.carrent.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PageUrl;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;

//TODO

public class CodeEntryCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "methed execute()");
		Router router;
		UserServiceImpl service = UserServiceImpl.getInstance();
		String generatedCode = request.getSession().getAttribute(SessionAttribute.GENERATE_CODE).toString();
		String email = request.getSession().getAttribute(SessionAttribute.USER_EMAIL).toString();
		String enteredCode = request.getParameter(RequestParameter.CODE);
		try {
			Optional<User> user = service.findByEmail(email);
			if (user.isPresent()) {
				if (generatedCode.equals(enteredCode)) {
					long userId = user.get().getUserId();
					service.updateStatus(userId, User.UserStatus.ACTIVE.ordinal() + 1);
					logger.log(Level.INFO, "the code is confirmed. Status changed to active");
					router = new Router(Router.RouterType.FORWARD, PageUrl.DEFAULT_PAGE);
				} else {
					logger.log(Level.ERROR, "the entered code is incorrect");
					request.setAttribute(SessionAttribute.ENTERED_CODE_ERROR, true);
					router = new Router(Router.RouterType.FORWARD, PageUrl.CODE_PAGE);
				}
			} else {
				logger.log(Level.ERROR, "user with email: {} is not found", email);
				router = new Router(Router.RouterType.FORWARD, PageUrl.ERROR_500_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error while entering code", e);
			router = new Router(Router.RouterType.FORWARD, PageUrl.ERROR_500_PAGE);
		}
		return router;
	}

}
