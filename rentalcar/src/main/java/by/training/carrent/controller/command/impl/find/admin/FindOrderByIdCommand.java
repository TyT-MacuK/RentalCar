package by.training.carrent.controller.command.impl.find.admin;

import java.util.Optional;

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

public class FindOrderByIdCommand implements Command {
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
		String id = request.getParameter(RequestParameter.ORDER_ID);
		if (id != null && !id.isBlank()) {
			long orderId = Long.parseLong(id);
			OrderServiceImpl service = OrderServiceImpl.getInstance();
			try {
				Optional<Order> orders = service.findById(orderId);
				request.setAttribute(RequestParameter.LIST_ORDERS, orders.stream().toList());
				router = new Router(PagePath.ADMIN_ORDERS_PAGE);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during find order by id: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			request.setAttribute(RequestParameter.ORDER_ID_INCORRECT, true);
			return new Router(PagePath.ADMIN_ORDERS_PAGE);
		}
		return router;
	}
}
