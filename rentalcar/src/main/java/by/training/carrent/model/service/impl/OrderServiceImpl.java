package by.training.carrent.model.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.OrderDaoImpl;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.OrderService;

import static by.training.carrent.controller.command.RequestParameter.*;

public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderServiceImpl instance = new OrderServiceImpl();
	private final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
	private static final int SECONDS_IN_ONE_DAY = 86400;
	private static final double HUNDRED_PERCENT = 100;

	private OrderServiceImpl() {
	}

	public static OrderServiceImpl getInstance() {
		return instance;
	}

	@Override
	public boolean add(Map<String, String> parameters) throws ServiceException {
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		try {
			String orderNumber = parameters.get(ORDER_NUMBER);
			LocalDate pickUpDate = LocalDate.parse(parameters.get(PICK_UP_DATE));
			LocalDate returnDate = LocalDate.parse(parameters.get(RETURN_DATE));
			long carId = Long.parseLong(parameters.get(CAR_ID));
			long userId = Long.parseLong(parameters.get(USER_ID));
			int discount = Integer.parseInt(parameters.get(DISCOUNT));

			Duration leaseDurationInSeconds = Duration.between(pickUpDate.atStartOfDay(), returnDate.atStartOfDay());
			int periodOfRent = (int) (leaseDurationInSeconds.getSeconds() / SECONDS_IN_ONE_DAY);

			BigDecimal cost = new BigDecimal(parameters.get(CAR_COST));
			cost = cost.multiply(new BigDecimal(periodOfRent));
			if (discount > 0) {
				BigDecimal moneyDiscount = cost.multiply(BigDecimal.valueOf(discount / HUNDRED_PERCENT));
				cost = cost.subtract(moneyDiscount.setScale(0, RoundingMode.UP));
			}
			Order order = new Order.Builder().setOrderNumber(orderNumber).setPrice(cost).setPickUpDate(pickUpDate).setReturnDate(returnDate)
					.setOrderStatus(Order.OrderStatus.AWAITS_PAYMENT).setCarId(carId).setUserId(userId).build();
			result = orderDao.add(order);

		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method add()", e);
			throw new ServiceException("Exception when add order", e);
		}
		return result;
	}

	@Override
	public List<Order> findAll() throws ServiceException {
		logger.log(Level.INFO, "method findAll()");
		try {
			return orderDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findAll()", e);
			throw new ServiceException("Exception when find all orders", e);
		}
	}

	@Override
	public Optional<Order> findById(long orderId) throws ServiceException {
		logger.log(Level.INFO, "method findById()");
		try {
			return orderDao.findById(orderId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findById()", e);
			throw new ServiceException("Exception when find orders by id", e);
		}
	}
	
	@Override
	public Optional<Order> findByOrderNumber(String orderNumber) throws ServiceException {
		logger.log(Level.INFO, "method findByOrderNumber()");
		try {
			return orderDao.findByOrderNumber(orderNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByOrderNumber()", e);
			throw new ServiceException("Exception when find order by number", e);
		}
	}

	@Override
	public List<Order> findByStatus(String status) throws ServiceException {
		logger.log(Level.INFO, "method findByStatus()");
		try {
			return orderDao.findByStatus(status);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByStatus()", e);
			throw new ServiceException("Exception when find orders by status", e);
		}
	}

	@Override
	public Optional<Order> findByCarId(long carId) throws ServiceException {
		logger.log(Level.INFO, "method findByCarId()");
		try {
			return orderDao.findByCarId(carId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByCarId()", e);
			throw new ServiceException("Exception when find orders by car id", e);
		}
	}

	@Override
	public Optional<Order> findByUserId(long userId) throws ServiceException {
		logger.log(Level.INFO, "method findByUserId()");
		try {
			return orderDao.findByUserId(userId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByUserId()", e);
			throw new ServiceException("Exception when find orders by user id", e);
		}
	}

	@Override
	public boolean updateStatus(long orderId, long statusId) throws ServiceException {
		logger.log(Level.INFO, "method updateStatus()");
		try {
			return orderDao.updateStatus(orderId, statusId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()", e);
			throw new ServiceException("Exception when update status", e);
		}
	}
}
