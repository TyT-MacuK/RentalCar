package by.training.carrent.model.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

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
import by.training.carrent.model.dao.UserDao;
import by.training.carrent.model.dao.impl.UserDaoImpl;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.UserService;

public class UserServiceImplTest {
	UserDao dao;
	UserService service;
	Map<String, String> parameters;

	@BeforeClass
	public void initialize() {
		dao = Mockito.mock(UserDaoImpl.class);
		Whitebox.setInternalState(UserDaoImpl.class, "instance", dao);
		service = UserServiceImpl.getInstance();
		parameters = new HashMap<>();
		parameters.put(RequestParameter.USER_EMAIL, "korozarecipient@gmail.com");
		parameters.put(RequestParameter.USER_PASSWORD, "12345");
		parameters.put(RequestParameter.USER_PASSWORD_FOR_AUTHENTICATION, "1");
		parameters.put(RequestParameter.USER_FIRST_NAME, "Jack");
		parameters.put(RequestParameter.USER_LAST_NAME, "London");
		parameters.put(RequestParameter.USER_DATE_OF_BIRTH, "1995-01-01");
		parameters.put(RequestParameter.USER_PHONE_NUMBER, "296957458");
	}

	@Test(description = "Testing method findAll")
	public void positiveRegisterUserTest() throws ServiceException, DaoException {
		when(dao.add(Mockito.any(User.class), Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
		boolean actual = service.registerUser(parameters);
		Assert.assertTrue(actual);
	}

	@Test(description = "Positive testing method findByEmail")
	public void positiveFindByEmailTest() throws ServiceException, DaoException {
		String email = "korozarecipient@gmail.com";
		when(dao.findByEmail(email)).thenReturn(Optional.of(new User()));
		Optional<User> actual = service.findByEmail(email);
		Assert.assertTrue(actual.isPresent());
	}

	@DataProvider(name = "createDataToNegativeFindByEmailTest")
	public Object[] createDataToNegativeFindByEmailTest() {
		return new Object[] { "1@!.1", " ", "qwer", null };
	}

	@Test(description = "Negative testing method findByEmail", dataProvider = "createDataToNegativeFindByEmailTest")
	public void negativeFindByEmailTest(String email) throws ServiceException, DaoException {
		when(dao.findByEmail(email)).thenReturn(Optional.empty());
		Optional<User> actual = service.findByEmail(email);
		Assert.assertFalse(actual.isPresent());
	}

	@DataProvider(name = "createDataToPositiveUpdateFirstNameTest")
	public Object[] createDataToPositiveUpdateFirstNameTest() {
		return new Object[][] { { 1, "Педро" }, { 5, "Antonio" } };
	}

	@Test(description = "Positive testing method updateFirstName", dataProvider = "createDataToPositiveUpdateFirstNameTest")
	public void positiveUpdateFirstNameTest(long userId, String name) throws DaoException, ServiceException {
		when(dao.updateFirstName(userId, name)).thenReturn(true);
		boolean actual = service.updateFirstName(userId, name);
		Assert.assertTrue(actual);
	}

	@DataProvider(name = "createDataToNegativeUpdateFirstNameTest")
	public Object[] createDataToNegativeUpdateFirstNameTest() {
		return new Object[][] { { 1, "Пе1дро" }, { 5, "Anto nio" }, { 2, " " }, { 4, null } };
	}

	@Test(description = "Negative testing method updateFirstName", dataProvider = "createDataToNegativeUpdateFirstNameTest")
	public void negativeUpdateFirstNameTest(long userId, String name) throws DaoException, ServiceException {
		when(dao.updateFirstName(userId, name)).thenReturn(false);
		boolean actual = service.updateFirstName(userId, name);
		Assert.assertFalse(actual);
	}

	@Test(description = "Testing method updateDiscount")
	public void updateDiscount() throws DaoException, ServiceException {
		long carId = 2;
		int discount = 10;
		when(dao.updateDiscount(carId, discount)).thenReturn(true);
		boolean actual = service.updateDiscount(carId, discount);
		Assert.assertTrue(actual);
	}

	@AfterClass
	public void tierDown() {
		dao = null;
		service = null;
		parameters = null;
	}
}
