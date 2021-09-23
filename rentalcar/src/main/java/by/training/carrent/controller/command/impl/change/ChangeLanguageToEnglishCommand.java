package by.training.carrent.controller.command.impl.change;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.SessionAttribute;

public class ChangeLanguageToEnglishCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String ENGLISH = "en";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		session.setAttribute(SessionAttribute.LOCALE, ENGLISH);
		Router router = new Router(session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
		router.setRedirect();
		logger.log(Level.INFO, "language was changed to English");
		return router;
	}

}
