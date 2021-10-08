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
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.OrderServiceImpl;

public class ChangeOrderStatus implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String STANDARD_STATUS = "choose status";

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
		String status = request.getParameter(RequestParameter.USER_STATUS);
		OrderServiceImpl service = OrderServiceImpl.getInstance();
		if (userId != null && !userId.isBlank() && !status.equals(STANDARD_STATUS)) {
			try {
				service.updateStatus(Long.parseLong(userId), Order.OrderStatus.valueOf(status).ordinal() + 1);
				router = new Router(PagePath.ADMIN_ORDERS_REDIRECT);
				router.setRedirect();
			} catch (NumberFormatException e) {
				logger.log(Level.ERROR, "unknown status: {}", status);
				router = new Router(PagePath.ERROR_500_PAGE);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during changing user status: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			request.setAttribute(RequestParameter.CHANGE_ORDERS_STATUS_INCORRECT, true);
			router = new Router(PagePath.ADMIN_ORDERS_PAGE);
		}
		return router;
	}
}
