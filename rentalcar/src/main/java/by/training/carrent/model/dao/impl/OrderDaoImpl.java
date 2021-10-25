package by.training.carrent.model.dao.impl;

import static by.training.carrent.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.connection.ConnectionPool;
import by.training.carrent.model.dao.ColumnName;
import by.training.carrent.model.dao.OrderDao;
import by.training.carrent.model.entity.Order;

public class OrderDaoImpl implements OrderDao {
	private static final Logger logger = LogManager.getLogger();
	private static final OrderDaoImpl instance = new OrderDaoImpl();
	private static final String SPASE = " ";
	private static final String UNDERSCORE = "_";
	private static final String SQL_CREATE_ORDER = """
			INSERT INTO orders (price, pick_up_date, return_date, order_status_id, car_id, user_id)
			VALUES
			(?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_FIND_BY_ID = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE order_id = ?
			""";
	private static final String SQL_FIND_BY_CAR_ID = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE car_id = ?
			""";
	private static final String SQL_FIND_BY_USER_ID = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE user_id = ?
			""";
	private static final String SQL_FIND_ORDERS_BY_USER_ID_AND_LIMIT = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE user_id = ?
			ORDER BY order_id DESC
			LIMIT ?, ?
			""";
	private static final String SQL_FIND_ORDERS_BY_LIMIT = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			ORDER BY order_id DESC
			LIMIT ?, ?
			""";
	private static final String SQL_FIND_CAR_ID_BY_USER_ID = "SELECT DISTINCT car_id FROM orders WHERE user_id = ?";
	private static final String SQL_FIND_CARS_ID_WHERE_UNPAID_ORDER = "SELECT DISTINCT car_id FROM orders WHERE order_status_id = ?";
	private static final String SQL_UPDATE_STATUS = "UPDATE orders SET order_status_id = ? WHERE order_id = ?";
	private static final String SQL_COUNT_ORDERS_TO_USER = "SELECT COUNT(order_id) AS count_orders FROM orders WHERE user_id = ?";
	private static final String SQL_COUNT_ORDERS = "SELECT COUNT(order_id) AS count_orders FROM orders";
	private static final String SQL_RETURN_ID = "SELECT LAST_INSERT_ID()";
	private static final String SQL_REJECT_UNPAID_ORDERS = "UPDATE orders SET order_status_id = ? WHERE order_status_id = ?";

	private OrderDaoImpl() {
	}

	public static OrderDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addAndReturnId(Order order) throws DaoException {
		logger.log(Level.INFO, "method addAndReturnId()");
		long id = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER);) {
			statement.setBigDecimal(1, order.getPrice());
			statement.setDate(2, Date.valueOf(order.getPickUpDate()));
			statement.setDate(3, Date.valueOf(order.getReturnDate()));
			statement.setLong(4, order.getOrderStatus().ordinal() + 1);
			statement.setLong(5, order.getCarId());
			statement.setLong(6, order.getUserId());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.executeQuery(SQL_RETURN_ID)) {
				while (resultSet.next()) {
					id = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method addAndReturnId()", e);
			throw new DaoException("Exception when add order and return id", e);
		}
		return id;
	}

	@Override
	public Optional<Order> findById(long id) throws DaoException {
		logger.log(Level.INFO, "method findById()");
		Optional<Order> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getDate(ORDER_PICK_UP_DATE).toLocalDate())
							.setReturnDate(resultSet.getDate(ORDER_RETURN_DATE).toLocalDate())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					result = Optional.ofNullable(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findById()", e);
			throw new DaoException("Exception when find order by id", e);
		}
		return result;
	}

	@Override
	public List<Order> findByCarId(long carId) throws DaoException {
		logger.log(Level.INFO, "method findByCarId()");
		List<Order> listOrders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CAR_ID)) {
			statement.setLong(1, carId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getDate(ORDER_PICK_UP_DATE).toLocalDate())
							.setReturnDate(resultSet.getDate(ORDER_RETURN_DATE).toLocalDate())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					listOrders.add(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByCarId()", e);
			throw new DaoException("Exception when find by car id", e);
		}
		return listOrders;
	}

	@Override
	public Optional<Order> findByUserId(long userId) throws DaoException {
		logger.log(Level.INFO, "method findByUserId()");
		Optional<Order> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getDate(ORDER_PICK_UP_DATE).toLocalDate())
							.setReturnDate(resultSet.getDate(ORDER_RETURN_DATE).toLocalDate())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					result = Optional.ofNullable(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByUserId()", e);
			throw new DaoException("Exception when find by user id", e);
		}
		return result;
	}

	@Override
	public List<Long> findCarsIdByUserId(long userId) throws DaoException {
		logger.log(Level.INFO, "method findByUserId()");
		List<Long> listCarsId = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_CAR_ID_BY_USER_ID)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					listCarsId.add(resultSet.getLong(CAR_ID));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByUserId()", e);
			throw new DaoException("Exception when find by user id", e);
		}
		return listCarsId;
	}

	@Override
	public List<Order> findByUserIdAndLimit(long userId, int leftBorder, int numberOfLines) throws DaoException {
		logger.log(Level.INFO, "method findByUserIdAndLimit()");
		List<Order> listOrders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_USER_ID_AND_LIMIT)) {
			statement.setLong(1, userId);
			statement.setInt(2, leftBorder);
			statement.setInt(3, numberOfLines);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getDate(ORDER_PICK_UP_DATE).toLocalDate())
							.setReturnDate(resultSet.getDate(ORDER_RETURN_DATE).toLocalDate())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					listOrders.add(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByUserIdAndLimit()", e);
			throw new DaoException("Exception when find orders by user id and limit", e);
		}
		return listOrders;
	}

	@Override
	public List<Order> findByLimit(int leftBorder, int numberOfLines) throws DaoException {
		logger.log(Level.INFO, "method findByLimit()");
		List<Order> listOrders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_LIMIT)) {
			statement.setInt(1, leftBorder);
			statement.setInt(2, numberOfLines);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getDate(ORDER_PICK_UP_DATE).toLocalDate())
							.setReturnDate(resultSet.getDate(ORDER_RETURN_DATE).toLocalDate())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					listOrders.add(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByLimit()", e);
			throw new DaoException("Exception when find orders by limit", e);
		}
		return listOrders;
	}

	@Override
	public boolean updateStatus(long orderId, long statusId) throws DaoException {
		logger.log(Level.INFO, "method updateStatus()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			statement.setLong(2, orderId);
			statement.setLong(1, statusId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()", e);
			throw new DaoException("Exception when update status", e);
		}
		return result;
	}

	@Override
	public int countOrders(long userId) throws DaoException {
		logger.log(Level.INFO, "method countOrders()");
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ORDERS_TO_USER);) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(COUNT_ORDERS);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method countOrders()", e);
			throw new DaoException("Exception when count orders", e);
		}
		return result;
	}

	@Override
	public int countOrders() throws DaoException {
		logger.log(Level.INFO, "method countOrders()");
		int result = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ORDERS);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				result = resultSet.getInt(COUNT_ORDERS);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method countOrders()", e);
			throw new DaoException("Exception when count orders", e);
		}
		return result;
	}

	@Override
	public void rejectUnpaidOrders() throws DaoException {
		logger.log(Level.INFO, "method rejectUnpaidOrders()");
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			connection.setAutoCommit(false);
			List<Long> carsId = new ArrayList<>();
			try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_CARS_ID_WHERE_UNPAID_ORDER)) {
				statement.setLong(1, Order.OrderStatus.AWAITS_PAYMENT.ordinal() + 1);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						carsId.add(resultSet.getLong(ColumnName.CAR_ID));
					}
				}
			}
			CarDaoImpl carDao = CarDaoImpl.getInstance();
			carDao.updateStatusOfCarsByListId(carsId);
			try (PreparedStatement statement = connection.prepareStatement(SQL_REJECT_UNPAID_ORDERS);) {
				statement.setLong(1, Order.OrderStatus.DECLINED.ordinal() + 1);
				statement.setLong(2, Order.OrderStatus.AWAITS_PAYMENT.ordinal() + 1);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method rejectUnpaidOrders()", e);
			throw new DaoException("Exception when reject unpaid orders", e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.log(Level.ERROR, "exception in method close()", e);
			}
		}
	}
}
