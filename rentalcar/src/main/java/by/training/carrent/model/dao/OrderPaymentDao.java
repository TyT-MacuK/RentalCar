package by.training.carrent.model.dao;

import java.math.BigDecimal;

import by.training.carrent.exception.DaoException;

/**
 * The Interface OrderPaymentDao.
 */
public interface OrderPaymentDao {

	/**
	 * Pay for order.
	 *
	 * @param cardNumber the card number
	 * @param cvv the cvv
	 * @param cost the cost
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean payForOrder(String cardNumber, String cvv, BigDecimal cost) throws DaoException;
}
