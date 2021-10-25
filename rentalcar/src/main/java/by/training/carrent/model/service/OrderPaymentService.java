package by.training.carrent.model.service;

import java.math.BigDecimal;

import by.training.carrent.exception.ServiceException;

/**
 * The Interface OrderPaymentService.
 */
public interface OrderPaymentService {

	/**
	 * Pay for the order.
	 *
	 * @param cardNumber the card number
	 * @param cvv the cvv
	 * @param cost the cost
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean payForOrder(String cardNumber, String cvv, BigDecimal cost) throws ServiceException;
	
}
