package by.training.carrent.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Order;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Add the order and return order id.
	 *
	 * @param parameters the order parameters
	 * @return the order id
	 * @throws ServiceException the service exception
	 */
	long addAndReturnId(Map<String, String> parameters) throws ServiceException;

	/**
	 * Find order by id.
	 *
	 * @param orderId the order id
	 * @return the optional order
	 * @throws ServiceException the service exception
	 */
	Optional<Order> findById(long orderId) throws ServiceException;

	/**
	 * Find orders by car id.
	 *
	 * @param carId the car id
	 * @return the list of orders
	 * @throws ServiceException the service exception
	 */
	List<Order> findByCarId(long carId) throws ServiceException;

	/**
	 * Find order by user id and limit.
	 *
	 * @param userId        the user id
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of orders
	 * @throws ServiceException the service exception
	 */
	List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws ServiceException;

	/**
	 * Find list of orders by limit.
	 *
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of orders
	 * @throws ServiceException the service exception
	 */
	List<Order> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;

	/**
	 * Find cars id in all orders of the current user by user id.
	 *
	 * @param userId the user id
	 * @return the list of cars id
	 * @throws ServiceException the service exception
	 */
	List<Long> findCarsIdByUserId(long userId) throws ServiceException;

	/**
	 * Update status.
	 *
	 * @param orderId  the order id
	 * @param statusId the status id
	 * @return true, if status was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateStatus(long orderId, long statusId) throws ServiceException;

	/**
	 * Count orders definite user.
	 *
	 * @param userId the user id
	 * @return the amount of orders definite user
	 * @throws ServiceException the service exception
	 */
	int countOrders(long userId) throws ServiceException;

	/**
	 * Count orders.
	 *
	 * @return the amount of orders
	 * @throws ServiceException the service exception
	 */
	int countOrders() throws ServiceException;

	/**
	 * Reject unpaid orders.
	 *
	 * @throws ServiceException the service exception
	 */
	void rejectUnpaidOrders() throws ServiceException;
}
