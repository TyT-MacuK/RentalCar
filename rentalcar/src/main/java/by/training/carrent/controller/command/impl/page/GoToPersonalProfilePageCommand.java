package by.training.carrent.controller.command.impl.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.SessionAttribute;

public class GoToPersonalProfilePageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {//TODO role
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.PERSONAL_PROFILE_REDIRECT);
		router = new Router(PagePath.PERSONAL_PROFILE_PAGE);
		return router;
	}

}
