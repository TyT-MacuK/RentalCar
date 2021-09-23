package by.training.carrent.controller.command.impl;

import static by.training.carrent.controller.command.SessionAttribute.IS_AUTHENTICATED;
import static by.training.carrent.controller.command.SessionAttribute.USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;

public class SignOutCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(USER, null);
		session.setAttribute(IS_AUTHENTICATED, false);
		router = new Router(PagePath.HOME_PAGE_REDIRECT);
		router.setRedirect();
		logger.log(Level.INFO, "user signs out from the system");
		return router;
	}

}
