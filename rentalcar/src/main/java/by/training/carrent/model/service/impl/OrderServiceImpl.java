package by.training.carrent.model.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.OrderDaoImpl;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.OrderService;
import by.training.carrent.model.service.UnpaidOrderTimerTask;

import static by.training.carrent.controller.command.RequestParameter.*;

public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderServiceImpl instance = new OrderServiceImpl();
	private final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
	private static final int SECONDS_IN_ONE_DAY = 86400;
	private static final double HUNDRED_PERCENT = 100;
	private static final long START_IN_TWO_MINUTES = 120000;
	private static final long STARTUP_FREQUENCY_IN_FIVE_MINUTES = 300000;
	private final Timer unpaidOrderTimer;

	private OrderServiceImpl() {
		unpaidOrderTimer = new Timer();
		unpaidOrderTimer.schedule(new UnpaidOrderTimerTask(), START_IN_TWO_MINUTES, STARTUP_FREQUENCY_IN_FIVE_MINUTES);
	}

	public static OrderServiceImpl getInstance() {
		return instance;
	}

	@Override
	public boolean add(Map<String, String> parameters) throws ServiceException {
		logger.log(Level.INFO, "method add()");
		try {
			LocalDate pickUpDate = LocalDate.parse(parameters.get(PICK_UP_DATE));
			LocalDate returnDate = LocalDate.parse(parameters.get(RETURN_DATE));
			long carId = Long.parseLong(parameters.get(CAR_ID));
			long userId = Long.parseLong(parameters.get(USER_ID));
			int userDiscount = Integer.parseInt(parameters.get(USER_DISCOUNT));
			int carDiscount = Integer.parseInt(parameters.get(CAR_DISCOUNT));
			int discount = userDiscount > carDiscount ? userDiscount : carDiscount;
			
			Duration leaseDurationInSeconds = Duration.between(pickUpDate.atStartOfDay(), returnDate.atStartOfDay());
			int periodOfRent = (int) (leaseDurationInSeconds.getSeconds() / SECONDS_IN_ONE_DAY);

			BigDecimal cost = new BigDecimal(parameters.get(CAR_COST));
			cost = cost.multiply(new BigDecimal(periodOfRent));
			if (discount > 0) {
				BigDecimal moneyDiscount = cost.multiply(BigDecimal.valueOf(discount / HUNDRED_PERCENT));
				cost = cost.subtract(moneyDiscount.setScale(0, RoundingMode.UP));
			}
			Order order = new Order.Builder().setPrice(cost).setPickUpDate(pickUpDate).setReturnDate(returnDate)
					.setOrderStatus(Order.OrderStatus.AWAITS_PAYMENT).setCarId(carId).setUserId(userId).build();
			return orderDao.add(order);

		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method add()", e);
			throw new ServiceException("Exception when add order", e);
		}
	}
	
	@Override
	public long addAndReturnId(Map<String, String> parameters) throws ServiceException {
		logger.log(Level.INFO, "method addAndReturnId()");
		try {
			LocalDate pickUpDate = LocalDate.parse(parameters.get(PICK_UP_DATE));
			LocalDate returnDate = LocalDate.parse(parameters.get(RETURN_DATE));
			long carId = Long.parseLong(parameters.get(CAR_ID));
			long userId = Long.parseLong(parameters.get(USER_ID));
			int userDiscount = Integer.parseInt(parameters.get(USER_DISCOUNT));
			int carDiscount = Integer.parseInt(parameters.get(CAR_DISCOUNT));
			int discount = userDiscount > carDiscount ? userDiscount : carDiscount;
			
			Duration leaseDurationInSeconds = Duration.between(pickUpDate.atStartOfDay(), returnDate.atStartOfDay());
			int periodOfRent = (int) (leaseDurationInSeconds.getSeconds() / SECONDS_IN_ONE_DAY);

			BigDecimal cost = new BigDecimal(parameters.get(CAR_COST));
			cost = cost.multiply(new BigDecimal(periodOfRent));
			if (discount > 0) {
				BigDecimal moneyDiscount = cost.multiply(BigDecimal.valueOf(discount / HUNDRED_PERCENT));
				cost = cost.subtract(moneyDiscount.setScale(0, RoundingMode.UP));
			}
			Order order = new Order.Builder().setPrice(cost).setPickUpDate(pickUpDate).setReturnDate(returnDate)
					.setOrderStatus(Order.OrderStatus.AWAITS_PAYMENT).setCarId(carId).setUserId(userId).build();
			return orderDao.addAndReturnId(order);

		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method addAndReturnId()", e);
			throw new ServiceException("Exception when add order and return id", e);
		}
	}

	@Override
	public List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws ServiceException {
		logger.log(Level.INFO, "method findByUserIdAndLimit()");
		try {
			return orderDao.findByUserIdAndLimit(userId, leftBorder, numberOfLines);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByUserIdAndLimit()", e);
			throw new ServiceException("Exception when find orders by user id and limit", e);
		}
	}
	
	@Override
	public List<Order> findByLimit(int leftBorder, int numberOfLines) throws ServiceException {
		logger.log(Level.INFO, "method findByLimit()");
		try {
			return orderDao.findByLimit(leftBorder, numberOfLines);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByLimit()", e);
			throw new ServiceException("Exception when find orders by limit", e);
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
	public List<Order> findByCarId(long carId) throws ServiceException {
		logger.log(Level.INFO, "method findByCarId()");
		try {
			return orderDao.findByCarId(carId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByCarId()", e);
			throw new ServiceException("Exception when find orders by car id", e);
		}
	}
	
	@Override
	public List<Long> findCarsIdByUserId(long userId) throws ServiceException {
		logger.log(Level.INFO, "method findCarsIdByUserId()");
		try {
			return orderDao.findCarsIdByUserId(userId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findCarsIdByUserId()", e);
			throw new ServiceException("Exception when find cars id by user id", e);
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

	@Override
	public int countOrders(long userId) throws ServiceException {
		logger.log(Level.INFO, "method countOrders()");
		try {
			return orderDao.countOrders(userId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method countOrders()", e);
			throw new ServiceException("Exception when count orders", e);
		}
	}

	@Override
	public int countOrders() throws ServiceException {
		logger.log(Level.INFO, "method countOrders()");
		try {
			return orderDao.countOrders();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method countOrders()", e);
			throw new ServiceException("Exception when count orders", e);
		}
	}

	@Override
	public void rejectUnpaidOrders() throws ServiceException {
		logger.log(Level.INFO, "method rejectUnpaidOrders()");
		try {
			orderDao.rejectUnpaidOrders();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method rejectUnpaidOrders()", e);
			throw new ServiceException("Exception when reject unpaid orders", e);
		}
	}
}
