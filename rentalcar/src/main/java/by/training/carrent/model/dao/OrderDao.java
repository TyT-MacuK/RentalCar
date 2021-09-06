package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Order;

public interface OrderDao extends BaseDao<Long, Order> {

	List<Order> findByStatus(String status) throws DaoException;

	Optional<Order> findByCarId(long carId) throws DaoException;

	Optional<Order> findByUserId(long userId) throws DaoException;
	
	boolean updateStatus(long orderId, long statusId) throws DaoException;
}
