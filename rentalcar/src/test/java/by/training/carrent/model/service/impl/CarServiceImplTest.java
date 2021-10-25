package by.training.carrent.model.service.impl;

import org.testng.annotations.Test;

import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.CarDao;
import by.training.carrent.model.dao.impl.CarDaoImpl;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.service.CarService;

import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Part;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CarServiceImplTest {
	CarDao dao;
	CarService service;
	Map<String, String> parameters;

	@BeforeClass
	public void initialize() {
		dao = Mockito.mock(CarDaoImpl.class);
		Whitebox.setInternalState(CarDaoImpl.class, "instance", dao);
		service = CarServiceImpl.getInstance();
		parameters = new HashMap<>();
		parameters.put(RequestParameter.CAR_MANUFACTURER, Car.CarManufacturer.MAZDA.name());
		parameters.put(RequestParameter.CAR_MODEL, "6");
		parameters.put(RequestParameter.CAR_YEAR, "2021");
		parameters.put(RequestParameter.CAR_CONDITIONER, "true");
		parameters.put(RequestParameter.CAR_TRANSMISSION, Car.CarTransmission.AUTOMATIC.name());
		parameters.put(RequestParameter.CAR_COST, "150");
		parameters.put(RequestParameter.CAR_DISCOUNT, "0");
		parameters.put(RequestParameter.CAR_STATUS, Car.CarStatus.FREE.name());
		parameters.put(RequestParameter.IMAGE_UPLOAD_DIRECTORY, "E:\\image.jpg");
	}
	
	@Test(description = "Testing method add")
	public void add() throws DaoException, ServiceException {
		when(dao.add(Mockito.any(Car.class))).thenReturn(true);
		boolean actual = service.add(parameters, Mockito.any(Part.class));
		Assert.assertTrue(actual);
	}

	@DataProvider(name = "dataToFindById")
	public Object[][] createDataToFindById() {
		return new Object[][] { { 1,
				new Car.Builder().setCarId(1).setCarManufacturer(Car.CarManufacturer.MAZDA)
						.setCarStatus(Car.CarStatus.FREE).setCarTransmission(Car.CarTransmission.AUTOMATIC).build() },
				{ 123, new Car.Builder().setCarId(123).setCarManufacturer(Car.CarManufacturer.SKODA)
						.setCarStatus(Car.CarStatus.IMPOSSIBLE_TO_RENT).setCarTransmission(Car.CarTransmission.MANUAL)
						.build() } };
	}

	@Test(description = "Testing method findById", dataProvider = "dataToFindById")
	public void findByIdTest(long id, Car car) throws DaoException, ServiceException {
		Optional<Car> actualCar = Optional.empty();
		when(dao.findById(id)).thenReturn(Optional.of(car));
		actualCar = service.findById(id);
		Assert.assertEquals(actualCar, Optional.of(car));
	}

	@DataProvider(name = "dataToUpdateStatus")
	public Object[][] createDataToUpdateStatus() {
		return new Object[][] { { 1, Car.CarStatus.FREE.ordinal(), true },
				{ 5, Car.CarStatus.BOOKED.ordinal(), true } };
	}

	@Test(description = "Testing method updateStatus", dataProvider = "dataToUpdateStatus")
	public void updateStatusTest(long carId, long carStatusId, boolean expected) throws ServiceException, DaoException {
		boolean actual = false;
		when(dao.updateStatus(carId, carStatusId)).thenReturn(expected);
		actual = service.updateStatus(carId, carStatusId);
		assertTrue(actual);
	}

	@AfterClass
	public void tierDown() {
		dao = null;
		service = null;
	}
}
