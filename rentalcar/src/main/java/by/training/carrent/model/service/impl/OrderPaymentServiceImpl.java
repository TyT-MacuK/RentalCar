package by.training.carrent.model.service.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.OrderPaymentDaoImpl;
import by.training.carrent.model.service.OrderPaymentService;
import by.training.carrent.model.validator.InputDataValidator;

public class OrderPaymentServiceImpl implements OrderPaymentService {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderPaymentServiceImpl instance = new OrderPaymentServiceImpl();
	private final OrderPaymentDaoImpl orderPaymentDao = OrderPaymentDaoImpl.getInstance();

	private OrderPaymentServiceImpl() {
	}

	public static OrderPaymentServiceImpl getInstance() {
		return instance;
	}

	@Override
	public boolean payForOrder(String cardNumber, String cvv, BigDecimal cost) throws ServiceException {
		logger.log(Level.INFO, "method payForTheOrder()");
		boolean result = false;
		InputDataValidator validator = InputDataValidator.getInstance();
		if (validator.isCardNumberValid(cardNumber) && validator.isCvvValid(cvv)
				&& cost.compareTo(BigDecimal.ZERO) > 0) {
			try {
				result = orderPaymentDao.payForOrder(cardNumber, cvv, cost);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "exception in method payForTheOrder()", e);
				throw new ServiceException("Exception when pay for the order", e);
			}
		}
		return result;
	}
}
