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

public class GoToErrorPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		logger.log(Level.INFO, "redirected to error 404 page");
		router = new Router(PagePath.ERROR_404_PAGE);
		router.setRedirect();
		return router;
	}

}
