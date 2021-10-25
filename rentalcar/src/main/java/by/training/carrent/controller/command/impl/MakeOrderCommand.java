package by.training.carrent.controller.command.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.CarServiceImpl;
import by.training.carrent.model.service.impl.OrderServiceImpl;

import static by.training.carrent.controller.command.RequestParameter.*;

public class MakeOrderCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Map<String, String> parameters = new HashMap<>();
		Router router;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		Car car = (Car) session.getAttribute(SessionAttribute.CAR);
		
		String pickUpDateString = request.getParameter(PICK_UP_DATE);
		String returnDateString = request.getParameter(RETURN_DATE);
		if (pickUpDateString == null || pickUpDateString.isBlank() || returnDateString == null
				|| returnDateString.isBlank()) {
			logger.log(Level.INFO, "user entered wrong dates");
			request.setAttribute(ORDER_INCORRECT_DATE, true);
			return new Router(PagePath.MAKE_ORDER_PAGE);
		}
		LocalDate pickUpDate = LocalDate.parse(pickUpDateString);
		LocalDate returnDate = LocalDate.parse(returnDateString);
		if (pickUpDate.isAfter(returnDate) || pickUpDate.isBefore(LocalDate.now())) {
			logger.log(Level.INFO, "user entered the pick up date of the lease after the return date of the lease");
			request.setAttribute(ORDER_PICK_UP_BEFORE_RETURN, true);
			return new Router(PagePath.MAKE_ORDER_PAGE);
		}

		parameters.put(USER_ID, Long.toString(user.getUserId()));
		parameters.put(CAR_ID, Long.toString(car.getCarId()));
		parameters.put(PICK_UP_DATE, pickUpDateString);
		parameters.put(RETURN_DATE, returnDateString);
		parameters.put(CAR_COST, car.getCost().toString());
		parameters.put(USER_DISCOUNT, Integer.toString(user.getDiscount()));
		parameters.put(CAR_DISCOUNT, Integer.toString(car.getDiscount()));

		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		CarServiceImpl carService = CarServiceImpl.getInstance();
		try {
			Optional<Car> optionalCar = carService.findById(car.getCarId());
			long orderId;
			if (optionalCar.isPresent() && optionalCar.get().getCarStatus() == Car.CarStatus.FREE) {
				orderId = orderService.addAndReturnId(parameters);
				session.setAttribute(SessionAttribute.ORDER_ID, orderId);
				carService.updateStatus(car.getCarId(), Car.CarStatus.BOOKED.ordinal() + 1);
				router = new Router(PagePath.PAYMENT_PAGE_REDIRECT);
				router.setRedirect();
			} else if (optionalCar.isPresent() && optionalCar.get().getCarStatus() == Car.CarStatus.BOOKED) {
				List<Order> listOrders = orderService.findByCarId(car.getCarId());
				if (!isCarFreeOnThisDate(pickUpDate, returnDate, listOrders)) {
					logger.log(Level.INFO, "car booked on this date");
					request.setAttribute(CAR_BOOKED, true);
					return new Router(PagePath.MAKE_ORDER_PAGE);
				}
				orderId = orderService.addAndReturnId(parameters);
				session.setAttribute(SessionAttribute.ORDER_ID, orderId);
				carService.updateStatus(car.getCarId(), Car.CarStatus.BOOKED.ordinal() + 1);
				router = new Router(PagePath.PAYMENT_PAGE_REDIRECT);
				router.setRedirect();
			} else {
				logger.log(Level.INFO, "car booked on this date");
				request.setAttribute(CAR_BOOKED, true);
				router = new Router(PagePath.MAKE_ORDER_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error during making order: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}

	private boolean isCarFreeOnThisDate(LocalDate pickUpDate, LocalDate returnDate, List<Order> listOrders) {
		int countTrueOptions = 0;
		for (Order order : listOrders) {
			if (returnDate.isBefore(order.getPickUpDate()) || pickUpDate.isAfter(order.getReturnDate())) {
				countTrueOptions++;
			}
		}
		return countTrueOptions == listOrders.size();
	}
}
