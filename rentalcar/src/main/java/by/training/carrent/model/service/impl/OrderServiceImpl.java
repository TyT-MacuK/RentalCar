package by.training.carrent.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.OrderDaoImpl;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderServiceImpl instance = new OrderServiceImpl();
	private static final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
	
	private OrderServiceImpl() {
	}
	
	public static OrderServiceImpl getInstance() {
		return instance;
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
