package by.training.carrent.controller.command.impl.page.admin;

import static by.training.carrent.controller.command.SessionAttribute.PREVIOUS_PAGE;
import static by.training.carrent.controller.command.SessionAttribute.USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.model.entity.User;

public class GoToAdminAddCarPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER);
		if (user.getRole() != User.UserRole.ADMIN) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		session.setAttribute(PREVIOUS_PAGE, PagePath.ADMIN_ADD_CAR_REDIRECT);
		return new Router(PagePath.ADMIN_ADD_CAR_PAGE);
	}
}
