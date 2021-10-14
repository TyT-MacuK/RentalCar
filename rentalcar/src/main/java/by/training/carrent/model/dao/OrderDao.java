package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Order;

public interface OrderDao extends BaseDao<Long, Order> {
	
	long addAndReturnId(Order order) throws DaoException;
	
	Optional<Order> findById(long orderId) throws DaoException;
	
	List<Order> findByCarId(long carId) throws DaoException;

	Optional<Order> findByUserId(long userId) throws DaoException;
	
	List<Long> findCarsIdByUserId(long userId) throws DaoException;

	List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws DaoException;
	
	List<Order> findByLimit(int leftBorder, int numberOfLines) throws DaoException;

	boolean updateStatus(long orderId, long statusId) throws DaoException;
	
	int countOrders(long userId) throws DaoException;
	
	int countOrders() throws DaoException; 
	
	void rejectUnpaidOrders() throws DaoException;
}
