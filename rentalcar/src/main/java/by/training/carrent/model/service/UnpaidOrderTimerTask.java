package by.training.carrent.model.service;

import java.util.TimerTask;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.service.impl.OrderServiceImpl;

public class UnpaidOrderTimerTask extends TimerTask {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void run() {
		logger.log(Level.INFO, "order timer task is checking all orders and reject unpaid");
		OrderServiceImpl orderService = OrderServiceImpl.getInstance();
		try {
			orderService.rejectUnpaidOrders();
		} catch (ServiceException e) {
			logger.log(Level.WARN, "exception in method rejectUnpaidOrders()");
		}
	}

}
