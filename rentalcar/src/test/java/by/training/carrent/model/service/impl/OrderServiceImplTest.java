package by.training.carrent.model.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.OrderDao;
import by.training.carrent.model.dao.impl.OrderDaoImpl;
import by.training.carrent.model.entity.Order;
import by.training.carrent.model.service.OrderService;

public class OrderServiceImplTest {
	OrderDao dao;
	OrderService service;
	Map<String, String> parameters;

	@BeforeClass
	public void initialize() {
		dao = mock(OrderDaoImpl.class);
		Whitebox.setInternalState(OrderDaoImpl.class, "instance", dao);
		service = OrderServiceImpl.getInstance();
		parameters = new HashMap<>();
		parameters.put(RequestParameter.PICK_UP_DATE, "2021-10-20");
		parameters.put(RequestParameter.RETURN_DATE, "2021-10-25");
		parameters.put(RequestParameter.CAR_ID, "1");
		parameters.put(RequestParameter.USER_ID, "3");
		parameters.put(RequestParameter.USER_DISCOUNT, "5");
		parameters.put(RequestParameter.CAR_DISCOUNT, "0");
		parameters.put(RequestParameter.CAR_COST, "100");
	}
	
	@Test(description = "Testing method addAndReturnId")
	public void addAndReturnId() throws DaoException, ServiceException {
		long expected = 1;
		when(dao.addAndReturnId(Mockito.any(Order.class))).thenReturn(expected);
		long actual = service.addAndReturnId(parameters);
		Assert.assertEquals(actual, expected);
	}

	@DataProvider(name = "dataToFindById")
	public Object[][] createDataToFindById() {
		return new Object[][] { { 1, new Order.Builder().setOrderId(1).setOrderStatus(Order.OrderStatus.PAID).build() },
				{ 123, new Order.Builder().setOrderId(123).setOrderStatus(Order.OrderStatus.DECLINED).build() } };
	}

	@Test(description = "Testing method findById", dataProvider = "dataToFindById")
	public void findById(long id, Order expected) throws DaoException, ServiceException {
		when(dao.findById(id)).thenReturn(Optional.of(expected));
		Optional<Order> actual = service.findById(id);
		Assert.assertEquals(actual, Optional.of(expected));
	}
	
	@Test(description = "Testing method updateStatus")
	public void updateStatus() throws DaoException, ServiceException {
		long orderId = 1;
		long statusId = Order.OrderStatus.PAID.ordinal() + 1;
		when(dao.updateStatus(orderId, statusId)).thenReturn(true);
		boolean actual = service.updateStatus(orderId, statusId);
		Assert.assertTrue(actual);
	}

	@AfterClass
	public void tierDown() {
		dao = null;
		service = null;
		parameters = null;
	}
}
