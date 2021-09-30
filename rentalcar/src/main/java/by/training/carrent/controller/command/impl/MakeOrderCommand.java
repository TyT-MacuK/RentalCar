package by.training.carrent.controller.command.impl;

import java.time.LocalDate;
import java.util.HashMap;
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
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.CarServiceImpl;
import by.training.carrent.model.service.impl.OrderServiceImpl;
import by.training.carrent.util.CodeGenerator;

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

		parameters.put(USER_ID, Long.toString(user.getUserId()));
		parameters.put(CAR_ID, Long.toString(car.getCarId()));
		parameters.put(PICK_UP_DATE, pickUpDateString);
		parameters.put(RETURN_DATE, returnDateString);
		parameters.put(CAR_COST, car.getCost().toString());
		int discount = user.getDiscount() > car.getDiscount() ? user.getDiscount() : car.getDiscount();
		parameters.put(RequestParameter.DISCOUNT, Integer.toString(discount));
		if (pickUpDateString != null && !pickUpDateString.isBlank() && returnDateString != null
				&& !returnDateString.isBlank()) {
			LocalDate pickUpDate = LocalDate.parse(pickUpDateString);
			LocalDate returnDate = LocalDate.parse(returnDateString);
			if (pickUpDate.isBefore(returnDate) && pickUpDate.isAfter(LocalDate.now())) {
				OrderServiceImpl orderService = OrderServiceImpl.getInstance();
				CarServiceImpl carService = CarServiceImpl.getInstance();
				try {
					Optional<Order> optionalOrder = orderService.findByCarId(car.getCarId());
					Optional<Car> optionalCar = carService.findById(car.getCarId());
					CodeGenerator codeGenerator = CodeGenerator.getInstance();
					String orderNumber = codeGenerator.generateOrderNumber();
					parameters.put(ORDER_NUMBER, orderNumber);
					if (optionalOrder.isPresent() && optionalCar.isPresent()) {
						Order order = optionalOrder.get();
						car = optionalCar.get();
						if (car.getCarStatus() == Car.CarStatus.BOOKED && (returnDate.isBefore(order.getPickUpDate())
								|| pickUpDate.isAfter(order.getReturnDate()))) {
							orderService.add(parameters);
							session.setAttribute(SessionAttribute.ORDER_NUMBER, orderNumber);
							router = new Router(PagePath.PAYMENT_PAGE);
						} else {
							logger.log(Level.INFO, "car booked on this date");
							request.setAttribute(CAR_BOOKED, true);
							router = new Router(PagePath.MAKE_ORDER_PAGE);
						}
					} else if (optionalCar.isPresent() && optionalCar.get().getCarStatus() == Car.CarStatus.FREE) {
						orderService.add(parameters);
						carService.updateStatus(car.getCarId(), Car.CarStatus.BOOKED.ordinal() + 1);
						router = new Router(PagePath.PAYMENT_PAGE);
						session.setAttribute(SessionAttribute.ORDER_NUMBER, orderNumber);
					} else {
						logger.log(Level.INFO, "car booked on this date");
						request.setAttribute(CAR_BOOKED, true);
						router = new Router(PagePath.MAKE_ORDER_PAGE);
					}
				} catch (ServiceException e) {
					logger.log(Level.ERROR, "error during making order: ", e);
					router = new Router(PagePath.ERROR_500_PAGE);
				}
			} else {
				logger.log(Level.INFO, "pick up date more than return date");
				request.setAttribute(ORDER_WRONG_DATE, true);
				router = new Router(PagePath.MAKE_ORDER_PAGE);
			}
		} else {
			logger.log(Level.INFO, "user entered wrong dates");
			request.setAttribute(ORDER_WRONG_DATE, true);
			router = new Router(PagePath.MAKE_ORDER_PAGE);
		}
		return router;
	}
}
