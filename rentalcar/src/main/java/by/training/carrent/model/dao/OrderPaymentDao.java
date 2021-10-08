package by.training.carrent.model.dao;

import java.math.BigDecimal;

import by.training.carrent.exception.DaoException;

public interface OrderPaymentDao {

	boolean payForOrder(String cardNumber, String cvv, BigDecimal cost) throws DaoException;
}
