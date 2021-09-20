package by.training.carrent.controller.command.impl.change;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.SessionAttribute;

public class ChangeLanguageToRussianCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String RUSSIAN = "ru";
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		session.setAttribute(SessionAttribute.LOCALE, RUSSIAN);
		Router router = new Router(session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
		router.setRedirect();
		logger.log(Level.INFO, "language was changed to Russian");
		return router;
	}

}
