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

public class ChangeUserDiscountCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		if (!user.getRole().equals(User.UserRole.ADMIN)) {
			return new Router(PagePath.ERROR_403_PAGE);
		}
		String userId = request.getParameter(RequestParameter.USER_ID);
		String discount = request.getParameter(RequestParameter.USER_DISCOUNT);
		UserServiceImpl service = UserServiceImpl.getInstance();
		if (userId != null && !userId.isBlank() && discount != null && !discount.isBlank()) {
			try {
				service.updateDiscount(Long.parseLong(userId), Integer.parseInt(discount));
				router = new Router(PagePath.ADMIN_USERS_REDIRECT);
				router.setRedirect();
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during changing user discount: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			router = new Router(PagePath.ADMIN_USERS_PAGE);
			request.setAttribute(RequestParameter.CHANGE_DISCOUNT_INCORRECT, true);
		}
		return router;
	}
}
