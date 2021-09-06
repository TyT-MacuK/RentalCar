package by.training.carrent.model.dao.impl;

import static by.training.carrent.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.connection.ConnectionPool;
import by.training.carrent.model.dao.UserDao;
import by.training.carrent.model.entity.User;

//TODO password? In sql it cannot be null
//TODO remove

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final UserDaoImpl INSTANCE = new UserDaoImpl();
	private static final String SPASE = " ";
	private static final String UNDERSCORE = "_";
	private static final String SQL_CREATE_USER = """
			INSERT INTO users (email, first_name, last_name, discount,
			 phone_number, date_of_birth, user_status_id, user_role_id)
			VALUES
			(?, ?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_FIND_ALL = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id;
			""";
	private static final String SQL_FIND_BY_ID = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 WHERE user_id = ?;
			""";
	private static final String SQL_FIND_BY_DATE_OF_BIRTH = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 WHERE date_of_birth = ?;
			""";
	private static final String SQL_UPDATE_EMAIL = "UPDATE users SET email = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_DISCOUNT = "UPDATE users SET discount = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_PHONE_NUMBER = "UPDATE users SET phone_number = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_STATUS = "UPDATE users SET user_status_id = ? WHERE user_id = ?";

	private UserDaoImpl() {
	}

	public static UserDaoImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public List<User> findAll() throws DaoException {
		logger.log(Level.INFO, "method findAll()");
		List<User> result = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				User user = new User.Builder().setUserId(resultSet.getLong(USER_ID))
						.setEmail(resultSet.getString(USER_EMAIL)).setFirstName(resultSet.getString(USER_FIRST_NAME))
						.setLastName(resultSet.getString(USER_LAST_NAME)).setDiscont(resultSet.getInt(USER_DISCOUNT))
						.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER))
						.setDateOfBirth(resultSet.getDate(USER_DATE_OF_BIRTH).toLocalDate())
						.setStatus(User.UserStatus.valueOf(resultSet.getString(8).replace(SPASE, UNDERSCORE)))
						.setRole(User.UserRole.valueOf(resultSet.getString(9))).build();
				result.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findAll()");
			throw new DaoException("Exception when find all users", e);
		}
		return result;
	}

	@Override
	public Optional<User> findById(Long id) throws DaoException {
		logger.log(Level.INFO, "method findById()");
		Optional<User> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					User user = new User.Builder().setUserId(resultSet.getLong(USER_ID))
							.setEmail(resultSet.getString(USER_EMAIL))
							.setFirstName(resultSet.getString(USER_FIRST_NAME))
							.setLastName(resultSet.getString(USER_LAST_NAME))
							.setDiscont(resultSet.getInt(USER_DISCOUNT))
							.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER))
							.setDateOfBirth(resultSet.getDate(USER_DATE_OF_BIRTH).toLocalDate())
							.setStatus(User.UserStatus.valueOf(resultSet.getString(8).replace(SPASE, UNDERSCORE)))
							.setRole(User.UserRole.valueOf(resultSet.getString(9))).build();
					result = Optional.of(user);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findById()");
			throw new DaoException("Exception when find user by id", e);
		}
		return result;
	}

	@Override
	public boolean add(User user) throws DaoException {// TODO password? In sql it cannot be null
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setInt(4, user.getDiscont());
			statement.setString(5, user.getPhoneNumber());
			statement.setDate(6, Date.valueOf(user.getDateOfBirth()));
			statement.setLong(7, user.getStatus().ordinal() + 1);
			statement.setLong(8, user.getRole().ordinal() + 1);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method add()");
			throw new DaoException("Exception when add user", e);
		}
		return result;
	}

	@Override
	public boolean remove(User user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findByDateOfBirth(LocalDate dateOfBirth) throws DaoException {
		logger.log(Level.INFO, "method findByDateOfBirth()");
		List<User> result = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_DATE_OF_BIRTH)) {
			statement.setDate(1, Date.valueOf(dateOfBirth));
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					User user = new User.Builder().setUserId(resultSet.getLong(USER_ID))
							.setEmail(resultSet.getString(USER_EMAIL))
							.setFirstName(resultSet.getString(USER_FIRST_NAME))
							.setLastName(resultSet.getString(USER_LAST_NAME))
							.setDiscont(resultSet.getInt(USER_DISCOUNT))
							.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER))
							.setDateOfBirth(resultSet.getDate(USER_DATE_OF_BIRTH).toLocalDate())
							.setStatus(User.UserStatus.valueOf(resultSet.getString(8).replace(SPASE, UNDERSCORE)))
							.setRole(User.UserRole.valueOf(resultSet.getString(9))).build();
					result.add(user);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByDateOfBirth()");
			throw new DaoException("Exception when find users by date of birth", e);
		}
		return result;
	}

	@Override
	public boolean updateEmail(long userId, String email) throws DaoException {
		logger.log(Level.INFO, "method updateEmail()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EMAIL)) {
			statement.setLong(2, userId);
			statement.setString(1, email);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateEmail()");
			throw new DaoException("Exception when update email", e);
		}
		return result;
	}

	@Override
	public boolean updateDiscount(long userId, int discount) throws DaoException {
		logger.log(Level.INFO, "method updateDiscount()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DISCOUNT)) {
			statement.setLong(2, userId);
			statement.setInt(1, discount);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateDiscount()");
			throw new DaoException("Exception when update discount", e);
		}
		return result;
	}

	@Override
	public boolean updatePhoneNumber(long userId, String phoneNumber) throws DaoException {
		logger.log(Level.INFO, "method updatePhoneNumber()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PHONE_NUMBER)) {
			statement.setLong(2, userId);
			statement.setString(1, phoneNumber);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updatePhoneNumber()");
			throw new DaoException("Exception when update phone number", e);
		}
		return result;
	}

	@Override
	public boolean updateStatus(long userId, long userStatusId) throws DaoException {
		logger.log(Level.INFO, "method updateStatus()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			statement.setLong(2, userId);
			statement.setLong(1, userStatusId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()");
			throw new DaoException("Exception when update status", e);
		}
		return result;
	}

}
