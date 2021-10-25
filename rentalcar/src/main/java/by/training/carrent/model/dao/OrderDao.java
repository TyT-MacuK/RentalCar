package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Order;

/**
 * The Interface OrderDao.
 */
public interface OrderDao extends BaseDao<Long, Order> {

	/**
	 * Add the order and return order id.
	 *
	 * @param order the order
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long addAndReturnId(Order order) throws DaoException;

	/**
	 * Find order by id.
	 *
	 * @param orderId the order id
	 * @return the optional
	 * @throws DaoException the dao exception
	 */
	Optional<Order> findById(long orderId) throws DaoException;

	/**
	 * Find order by car id.
	 *
	 * @param carId the car id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Order> findByCarId(long carId) throws DaoException;

	/**
	 * Find order by user id.
	 *
	 * @param userId the user id
	 * @return the optional
	 * @throws DaoException the dao exception
	 */
	Optional<Order> findByUserId(long userId) throws DaoException;

	/**
	 * Find cars id in all orders of the current user by user id.
	 *
	 * @param userId the user id
	 * @return the list of cars id
	 * @throws DaoException the dao exception
	 */
	List<Long> findCarsIdByUserId(long userId) throws DaoException;

	/**
	 * Find orders by user id and limit.
	 *
	 * @param userId        the user id
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of orders
	 * @throws DaoException the dao exception
	 */
	List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws DaoException;

	/**
	 * Find orders by limit.
	 *
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of orders
	 * @throws DaoException the dao exception
	 */
	List<Order> findByLimit(int leftBorder, int numberOfLines) throws DaoException;

	/**
	 * Update status.
	 *
	 * @param orderId  the order id
	 * @param statusId the status id
	 * @return true, if status was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateStatus(long orderId, long statusId) throws DaoException;

	/**
	 * Count orders.
	 *
	 * @param userId the user id
	 * @return the amount of orders definite user
	 * @throws DaoException the dao exception
	 */
	int countOrders(long userId) throws DaoException;

	/**
	 * Count orders.
	 *
	 * @return the amount of orders
	 * @throws DaoException the dao exception
	 */
	int countOrders() throws DaoException;

	/**
	 * Reject unpaid orders.
	 *
	 * @throws DaoException the dao exception
	 */
	void rejectUnpaidOrders() throws DaoException;
}
