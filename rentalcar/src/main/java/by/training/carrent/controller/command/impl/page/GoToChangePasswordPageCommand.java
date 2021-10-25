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

public class GoToChangePasswordPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CHANGE_PASSWORD_REDIRECT);
		return new Router(PagePath.CHANGE_PASSWORD_PAGE);
	}
}
