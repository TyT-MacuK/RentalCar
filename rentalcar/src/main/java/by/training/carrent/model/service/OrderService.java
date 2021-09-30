package by.training.carrent.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Order;

public interface OrderService {
	
	boolean add(Map<String, String> parameters) throws ServiceException;

	List<Order> findAll() throws ServiceException;

	Optional<Order> findById(long orderId) throws ServiceException;
	
	Optional<Order> findByOrderNumber(String orderNumber) throws ServiceException;

	List<Order> findByStatus(String status) throws ServiceException;

	Optional<Order> findByCarId(long carId) throws ServiceException;

	Optional<Order> findByUserId(long userId) throws ServiceException;

	boolean updateStatus(long orderId, long statusId) throws ServiceException;
}
