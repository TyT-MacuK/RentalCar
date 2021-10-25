package by.training.carrent.controller.command.impl.change.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;

public class ChangeUserRoleCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String STANDARD_ROLE = "choose role";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		if (!user.getRole().equals(User.UserRole.ADMIN)) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		UserServiceImpl service = UserServiceImpl.getInstance();
		String userId = request.getParameter(RequestParameter.USER_ID);
		String userRole = request.getParameter(RequestParameter.USER_ROLE);
		if (userId != null && !userId.isBlank() && !userRole.equals(STANDARD_ROLE)) {
			try {
				service.updateRole(Long.parseLong(userId), User.UserRole.valueOf(userRole).ordinal() + 1);
				router = new Router(PagePath.ADMIN_USERS_REDIRECT);
				router.setRedirect();
			} catch (NumberFormatException e) {
				logger.log(Level.ERROR, "unknown role: {}", userRole);
				router = new Router(PagePath.ERROR_500_PAGE);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during changing user status: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			router = new Router(PagePath.ADMIN_USERS_PAGE);
			request.setAttribute(RequestParameter.CHANGE_ROLE_INCORRECT, true);
		}
		return router;
	}
}
