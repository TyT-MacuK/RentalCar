package by.training.carrent.model.dao.impl;

import static by.training.carrent.model.dao.ColumnName.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.connection.ConnectionPool;
import by.training.carrent.model.dao.CarDao;
import by.training.carrent.model.entity.Car;

public class CarDaoImpl implements CarDao {

//TODO method remove
	private static final Logger logger = LogManager.getLogger();
	private final static CarDaoImpl instance = new CarDaoImpl();
	private static final String SPASE = " ";
	private static final String UNDERSCORE = "_";
	private static final String SQL_CREATE_CAR = """
			INSERT INTO cars (model, year, conditioner, cost, image_url,
			 car_transmission_id, car_manufacturer_id, car_status_id)
			VALUES
			(?, ?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_FIND_ALL = """
			 		SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			 		""";
	private static final String SQL_FIND_CARS_BY_LIMIT = """
			 		SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   LIMIT ?, ?
			 		""";
	private static final String SQL_FIND_BY_ID = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE car_id = ?
					""";
	private static final String SQL_FIND_BY_MODEL = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE model = ?
					""";
	private static final String SQL_FIND_BY_DISCOUNT = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE discount = ?
					""";
	private static final String SQL_FIND_BY_YEAR = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE year = ?
					""";
	private static final String SQL_FIND_BY_COST = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE cost = ?
					""";
	private static final String SQL_FIND_BY_MANUFACTURER = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE car_manufacturer.manufacturer = ?
					""";
	private static final String SQL_FIND_BY_STATUS = """
					SELECT car_id, car_manufacturer.manufacturer, model, discount, year,
			conditioner, cost, image_url, car_transmission.transmission, car_status.car_status
			   FROM cars
			   JOIN car_transmission ON cars.car_transmission_id = car_transmission.transmission_id
			   JOIN car_manufacturer ON cars.car_manufacturer_id = car_manufacturer.manufacturer_id
			   JOIN car_status ON cars.car_status_id = car_status.car_status_id
			   WHERE car_status.car_status = ?
					""";
	private static final String SQL_UPDATE_DISCOUNT = "UPDATE cars SET discount = ? WHERE car_id = ?";
	private static final String SQL_UPDATE_COST = "UPDATE cars SET cost = ? WHERE car_id = ?";
	private static final String SQL_UPDATE_STATUS = "UPDATE cars SET cars.car_status_id = ? WHERE car_id = ?";
	private static final String SQL_COUNT_CARS = "SELECT COUNT(car_id) AS count_cars FROM cars";

	private CarDaoImpl() {
	}

	public static CarDaoImpl getInstance() {
		return instance;
	}

	public boolean add(Car car) throws DaoException {
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_CAR)) {
			statement.setString(1, car.getModel());
			statement.setInt(2, car.getYear());
			statement.setBoolean(3, car.isConditioner());
			statement.setBigDecimal(4, car.getCost());
			statement.setString(5, car.getImageUrl());
			statement.setLong(6, car.getCarTransmission().ordinal() + 1);
			statement.setLong(7, car.getCarManufacturer().ordinal() + 1);
			statement.setLong(8, car.getCarStatus().ordinal() + 1);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method add()", e);
			throw new DaoException("Exception when add car", e);
		}
		return result;
	}

	@Override
	public boolean remove(Car car) {
		boolean result = false;
		// TODO
		return result;
	}

	@Override
	public List<Car> findAll() throws DaoException {
		logger.log(Level.INFO, "method findAll()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
				ResultSet resultSet = statement.executeQuery()) {
			listCars = createListCars(resultSet);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findAll()", e);
			throw new DaoException("Exception when find all cars", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByLimit(int leftBorder, int numberOfLines) throws DaoException {
		logger.log(Level.INFO, "method findByLimit()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_CARS_BY_LIMIT)) {
			statement.setInt(1, leftBorder);
			statement.setInt(2, numberOfLines);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByLimit()", e);
			throw new DaoException("Exception when find cars by limit", e);
		}
		return listCars;
	}

	@Override
	public Optional<Car> findById(Long id) throws DaoException {
		logger.log(Level.INFO, "method findById()");
		Optional<Car> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Car car = new Car.Builder().setCarId(resultSet.getLong(CAR_ID))
							.setModel(resultSet.getString(CAR_MODEL)).setDiscount(resultSet.getInt(CAR_DISCOUNT))
							.setYear(resultSet.getInt(CAR_YEAR)).setConditioner(resultSet.getBoolean(CAR_CONDITIONER))
							.setCost(resultSet.getBigDecimal(CAR_COST)).setImageUrl(resultSet.getString(CAR_IMAGE_URI))
							.setCarTransmission(Car.CarTransmission.valueOf(resultSet.getString(9)))
							.setCarManufacturer(Car.CarManufacturer.valueOf(resultSet.getString(2)))
							.setCarStatus(Car.CarStatus.valueOf(resultSet.getString(10).replace(SPASE, UNDERSCORE)))
							.build();
					result = Optional.ofNullable(car);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findById()", e);
			throw new DaoException("Exception when find by id car", e);
		}
		return result;
	}

	@Override
	public List<Car> findByModel(String model) throws DaoException {
		logger.log(Level.INFO, "method findByModel()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MODEL)) {
			statement.setString(1, model);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByModel()", e);
			throw new DaoException("Exception when find by model car", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByDiscount(int discount) throws DaoException {
		logger.log(Level.INFO, "method findByModel()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_DISCOUNT)) {
			statement.setInt(1, discount);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByDiscount()", e);
			throw new DaoException("Exception when find by discount", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByYear(int year) throws DaoException {
		logger.log(Level.INFO, "method findByYear()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_YEAR)) {
			statement.setInt(1, year);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByYear()", e);
			throw new DaoException("Exception when find by year", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByCost(BigDecimal cost) throws DaoException {
		logger.log(Level.INFO, "method findByCost()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_COST)) {
			statement.setBigDecimal(1, cost);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByCost()", e);
			throw new DaoException("Exception when find by cost", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByManufacture(String carManufacturer) throws DaoException {
		logger.log(Level.INFO, "method findByManufacture()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MANUFACTURER)) {
			statement.setString(1, carManufacturer);
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByManufacture()", e);
			throw new DaoException("Exception when find by manufacture", e);
		}
		return listCars;
	}

	@Override
	public List<Car> findByStatus(String carStatus) throws DaoException {
		logger.log(Level.INFO, "method findByStatus()");
		List<Car> listCars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS)) {
			statement.setString(1, carStatus.replace(UNDERSCORE, SPASE));
			try (ResultSet resultSet = statement.executeQuery()) {
				listCars = createListCars(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByStatus()", e);
			throw new DaoException("Exception when find by status", e);
		}
		return listCars;
	}

	@Override
	public boolean updateDiscount(long carId, int discount) throws DaoException {
		logger.log(Level.INFO, "method updateDiscount()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DISCOUNT)) {
			statement.setLong(2, carId);
			statement.setInt(1, discount);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateDiscount()", e);
			throw new DaoException("Exception when update discount", e);
		}
		return result;
	}

	@Override
	public boolean updateCost(long carId, BigDecimal cost) throws DaoException {
		logger.log(Level.INFO, "method updateCost()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COST)) {
			statement.setLong(2, carId);
			statement.setBigDecimal(1, cost);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateCost()", e);
			throw new DaoException("Exception when update cost", e);
		}
		return result;
	}

	@Override
	public boolean updateStatus(long carId, long carStatusId) throws DaoException {
		logger.log(Level.INFO, "method updateStatus()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			statement.setLong(2, carId);
			statement.setLong(1, carStatusId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updaupdateStatusteModel()", e);
			throw new DaoException("Exception when update status", e);
		}
		return result;
	}

	@Override
	public int countCars() throws DaoException {
		logger.log(Level.INFO, "method countCars()");
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_COUNT_CARS)) {
			while (resultSet.next()) {
				result = resultSet.getInt(COUNT_CARS);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method countCars()", e);
			throw new DaoException("Exception when count cars", e);
		}
		return result;
	}

	private List<Car> createListCars(ResultSet resultSet) throws DaoException {
		logger.log(Level.INFO, "method createListCars()");
		List<Car> listCars = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Car car = new Car.Builder().setCarId(resultSet.getLong(CAR_ID)).setModel(resultSet.getString(CAR_MODEL))
						.setDiscount(resultSet.getInt(CAR_DISCOUNT)).setYear(resultSet.getInt(CAR_YEAR))
						.setConditioner(resultSet.getBoolean(CAR_CONDITIONER))
						.setCost(resultSet.getBigDecimal(CAR_COST)).setImageUrl(resultSet.getString(CAR_IMAGE_URI))
						.setCarTransmission(Car.CarTransmission.valueOf(resultSet.getString(9)))
						.setCarManufacturer(Car.CarManufacturer.valueOf(resultSet.getString(2)))
						.setCarStatus(Car.CarStatus.valueOf(resultSet.getString(10).replace(SPASE, UNDERSCORE)))
						.build();
				listCars.add(car);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method createListCars()", e);
			throw new DaoException("Exception when add new car to list", e);
		}
		return listCars;
	}
}
