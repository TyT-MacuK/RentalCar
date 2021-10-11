package by.training.carrent.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Order;

public interface OrderService {

	boolean add(Map<String, String> parameters) throws ServiceException;

	long addAndReturnId(Map<String, String> parameters) throws ServiceException;

	Optional<Order> findById(long orderId) throws ServiceException;

	List<Order> findByStatus(String status) throws ServiceException;

	List<Order> findByCarId(long carId) throws ServiceException;

	List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws ServiceException;

	List<Order> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;

	List<Long> findCarsIdByUserId(long userId) throws ServiceException;

	boolean updateStatus(long orderId, long statusId) throws ServiceException;

	int countOrders(long userId) throws ServiceException;

	int countOrders() throws ServiceException;
	
	void rejectUnpaidOrders() throws ServiceException;
}
