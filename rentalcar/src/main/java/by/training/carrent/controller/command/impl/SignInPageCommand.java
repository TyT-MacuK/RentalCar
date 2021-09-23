package by.training.carrent.controller.command.impl;

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
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;

import static by.training.carrent.controller.command.SessionAttribute.*;

public class SignInPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(PREVIOUS_PAGE, PagePath.SIGN_IN_PAGE_REDIRECT);
		String email = request.getParameter(RequestParameter.USER_EMAIL);
		String password = request.getParameter(RequestParameter.USER_PASSWORD);
		UserServiceImpl service = UserServiceImpl.getInstance();
		try {
			Optional<User> user = service.findByEmailAndPassword(email, password);
			if (user.isPresent()) {
				User localUser = user.get();
				if (localUser.getStatus() == User.UserStatus.ACTIVE) {
					session.setAttribute(USER, localUser);
					session.setAttribute(IS_AUTHENTICATED, true);
				}
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
			} else {
				logger.log(Level.INFO, "user is not found");
				request.setAttribute(RequestParameter.AUTHENTICATION_ERROR, true);
				router = new Router(PagePath.SIGN_IN_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error during sign in user: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		logger.log(Level.INFO, "user signs in the system");
		return router;
	}
}
