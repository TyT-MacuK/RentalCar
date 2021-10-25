package by.training.carrent.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.connection.ConnectionPool;
import by.training.carrent.model.dao.ColumnName;
import by.training.carrent.model.dao.OrderPaymentDao;

public class OrderPaymentDaoImpl implements OrderPaymentDao {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderPaymentDaoImpl instance = new OrderPaymentDaoImpl();
	private static final String SQL_FIND_BALANCE_BY_NUMBER_AND_CVV = "SELECT balance FROM payment_card WHERE card_number = ? AND cvv = ?";
	private static final String SQL_UPDATE_BALANCE = "UPDATE payment_card SET balance = ? WHERE card_number = ? AND cvv = ?";

	private OrderPaymentDaoImpl() {
	}

	public static OrderPaymentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public boolean payForOrder(String cardNumber, String cvv, BigDecimal cost) throws DaoException {
		logger.log(Level.INFO, "method payForOrder()");
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			connection.setAutoCommit(false);
			BigDecimal balance = null;
			try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BALANCE_BY_NUMBER_AND_CVV)) {
				statement.setString(1, cardNumber);
				statement.setString(2, cvv);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						balance = resultSet.getBigDecimal(ColumnName.PAYMENT_CARD_BALANCE);
					}
				}
			}
			if (balance != null && cost.compareTo(balance) < 0) {
				try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
					statement.setBigDecimal(1, balance.subtract(cost));
					statement.setString(2, cardNumber);
					statement.setString(3, cvv);
					result = statement.executeUpdate() > 0;
					connection.commit();
				}
			} else {
				logger.log(Level.ERROR, "not enough money in the account");
				connection.rollback();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, "exception in method rollback()", e1);
				throw new DaoException("Exception during transaction rollback", e1);
			}
			logger.log(Level.ERROR, "exception in method payForOrder()", e);
			throw new DaoException("Exception when try to pay to order", e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.log(Level.ERROR, "exception in method close()", e);
			}
		}
		return result;
	}
}
