package by.training.carrent.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.impl.OrderServiceImpl;

public class PaymentCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "methed execute()");
		Router router;
		HttpSession session = request.getSession();
		String cardNumber = request.getParameter("card_number");   //TODO we need it???
		String cvv = request.getParameter("cvv");                  //TODO we need it???
		String orderNumber = (String) session.getAttribute(SessionAttribute.ORDER_NUMBER);
		OrderServiceImpl service = OrderServiceImpl.getInstance();
		try {
			Optional<Order> order = service.findByOrderNumber(orderNumber);
			if (order.isPresent()) {
				service.updateStatus(order.get().getOrderId(), Order.OrderStatus.PAID.ordinal() + 1);
				logger.log(Level.INFO, "Status changed to paid");
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
			} else {
				router = new Router(PagePath.PAYMENT_PAGE);//TODO what page here?
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error while payment order", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
