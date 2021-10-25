package by.training.carrent.model.service.impl;

import org.testng.annotations.Test;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.OrderPaymentDao;
import by.training.carrent.model.dao.impl.OrderPaymentDaoImpl;
import by.training.carrent.model.service.OrderPaymentService;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class OrderPaymentServiceImplTest {
	OrderPaymentDao dao;
	OrderPaymentService service;

	@BeforeClass
	public void initialize() {
		dao = Mockito.mock(OrderPaymentDaoImpl.class);
		Whitebox.setInternalState(OrderPaymentDaoImpl.class, "instance", dao);
		service = OrderPaymentServiceImpl.getInstance();
	}

	@DataProvider
	public Object[][] createDataToPositivePayForOrder() {
		return new Object[][] { { "1234123412341234", "123", new BigDecimal(52), true },
				{ "4321432143214321", "321", new BigDecimal(67.36), true } };
	}

	@Test(dataProvider = "createDataToPositivePayForOrder")
	public void positivePayForOrder(String cardNumber, String cvv, BigDecimal cost, boolean expected)
			throws ServiceException, DaoException {
		when(dao.payForOrder(cardNumber, cvv, cost)).thenReturn(expected);
		boolean actual = service.payForOrder(cardNumber, cvv, cost);
		Assert.assertTrue(actual);
	}

	@DataProvider
	public Object[][] createDataToNegativePayForOrder() {
		return new Object[][] { { "1234", "123", new BigDecimal(52) },
				{ "4321432143214321", "1", new BigDecimal(67.36) }, { "4321432143214321", "123", new BigDecimal(0) },
				{ "", "123", new BigDecimal(67.36) }, { "4321432143214321", null, new BigDecimal(67.36) } };
	}

	@Test(dataProvider = "createDataToNegativePayForOrder")
	public void negativePayForOrder(String cardNumber, String cvv, BigDecimal cost)
			throws ServiceException, DaoException {
		boolean actual = service.payForOrder(cardNumber, cvv, cost);
		Assert.assertFalse(actual);
	}

	@AfterClass
	public void tierDown() {
		dao = null;
		service = null;
	}
}
