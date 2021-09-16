package by.training.carrent.model.dao.impl;

import static by.training.carrent.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.connection.ConnectionPool;
import by.training.carrent.model.dao.OrderDao;
import by.training.carrent.model.entity.Order;

//TODO method remove

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
	private static final String SQL_FIND_ALL = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			""";
	private static final String SQL_FIND_BY_ID = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE order_id = ?
			""";
	private static final String SQL_FIND_BY_STATUS = """
			SELECT order_id, price, pick_up_date, return_date, order_status, car_id, user_id
			FROM orders
			JOIN order_status ON orders.order_status_id = order_status.order_status_id
			WHERE order_status = ?
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
	private static final String SQL_UPDATE_STATUS = "UPDATE orders SET order_status_id = ? WHERE order_id = ?";

	private OrderDaoImpl() {
	}

	public static OrderDaoImpl getInstance() {
		return instance;
	}
	
	public boolean add(Order order) throws DaoException {
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER);) {
			statement.setBigDecimal(1, order.getPrice());
			statement.setTimestamp(2, Timestamp.valueOf(order.getPickUpDate()));
			statement.setTimestamp(3, Timestamp.valueOf(order.getReturnDate()));
			statement.setLong(4, order.getOrderStatus().ordinal() + 1);
			statement.setLong(5, order.getCarId());
			statement.setLong(6, order.getUserId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method add()", e);
			throw new DaoException("Exception when add order", e);
		}
		return result;
	}

	@Override
	public List<Order> findAll() throws DaoException {
		logger.log(Level.INFO, "method findAll()");
		List<Order> listOrders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
						.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
						.setPickUpDate(resultSet.getTimestamp(ORDER_PICK_UP_DATE).toLocalDateTime())
						.setReturnDate(resultSet.getTimestamp(ORDER_RETURN_DATE).toLocalDateTime())
						.setOrderStatus(Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
						.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
				listOrders.add(order);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findAll()", e);
			throw new DaoException("Exception when find all orders", e);
		}
		return listOrders;
	}

	@Override
	public Optional<Order> findById(Long id) throws DaoException {
		logger.log(Level.INFO, "method findById()");
		Optional<Order> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getTimestamp(ORDER_PICK_UP_DATE).toLocalDateTime())
							.setReturnDate(resultSet.getTimestamp(ORDER_RETURN_DATE).toLocalDateTime())
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
	public boolean remove(Order order) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> findByStatus(String status) throws DaoException {
		logger.log(Level.INFO, "method findByStatus()");
		List<Order> listOrders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS);) {
			statement.setString(1, status.replace(UNDERSCORE, SPASE));
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getTimestamp(ORDER_PICK_UP_DATE).toLocalDateTime())
							.setReturnDate(resultSet.getTimestamp(ORDER_RETURN_DATE).toLocalDateTime())
							.setOrderStatus(Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					listOrders.add(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByStatus()", e);
			throw new DaoException("Exception when find orders by status", e);
		}
		return listOrders;
	}

	@Override
	public Optional<Order> findByCarId(long carId) throws DaoException {
		logger.log(Level.INFO, "method findByCarId()");
		Optional<Order> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CAR_ID)) {
			statement.setLong(1, carId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order.Builder().setOrderId(resultSet.getLong(ORDER_ID))
							.setPrice(resultSet.getBigDecimal(ORDER_PRICE))
							.setPickUpDate(resultSet.getTimestamp(ORDER_PICK_UP_DATE).toLocalDateTime())
							.setReturnDate(resultSet.getTimestamp(ORDER_RETURN_DATE).toLocalDateTime())
							.setOrderStatus(
									Order.OrderStatus.valueOf(resultSet.getString(5).replace(SPASE, UNDERSCORE)))
							.setCarId(resultSet.getLong(6)).setUserId(resultSet.getLong(7)).build();
					result = Optional.ofNullable(order);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByCarId()", e);
			throw new DaoException("Exception when find by car id", e);
		}
		return result;
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
							.setPickUpDate(resultSet.getTimestamp(ORDER_PICK_UP_DATE).toLocalDateTime())
							.setReturnDate(resultSet.getTimestamp(ORDER_RETURN_DATE).toLocalDateTime())
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
}
