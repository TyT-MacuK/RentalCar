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
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.impl.CarServiceImpl;
import by.training.carrent.model.service.impl.OrderPaymentServiceImpl;
import by.training.carrent.model.service.impl.OrderServiceImpl;

import static by.training.carrent.controller.command.RequestParameter.*;

public class PaymentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String CARD_NUMBER = "card_number";
	private static final String CVV = "cvv";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "methed execute()");
		Router router;
		HttpSession session = request.getSession();
		String cardNumber = request.getParameter(CARD_NUMBER);
		String cvv = request.getParameter(CVV);
		long orderId = (long) session.getAttribute(SessionAttribute.ORDER_ID);
		Car car = (Car) session.getAttribute(SessionAttribute.CAR);
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		OrderPaymentServiceImpl paymentService = OrderPaymentServiceImpl.getInstance();
		try {
			Optional<Order> order = orderService.findById(orderId);
			if (!order.isPresent()) {
				logger.log(Level.ERROR, "order is not found");
				return new Router(PagePath.ERROR_500_PAGE);
			}
			if (paymentService.payForOrder(cardNumber, cvv, order.get().getPrice())) {
				orderService.updateStatus(order.get().getOrderId(), Order.OrderStatus.PAID.ordinal() + 1);
				logger.log(Level.INFO, "Status changed to paid");
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
			} else {
				logger.log(Level.ERROR, "not enough money to pay");
				orderService.updateStatus(orderId, Order.OrderStatus.DECLINED.ordinal() + 1);
				CarServiceImpl carService = CarServiceImpl.getInstance();
				carService.updateStatus(car.getCarId(), Car.CarStatus.FREE.ordinal() + 1);
				router = new Router(PagePath.PAYMENT_PAGE);
				request.setAttribute(NOT_ENOUGHT_MONEY_TO_PAY, true);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error while payment order", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}
}
