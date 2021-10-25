package by.training.carrent.model.dao.impl;

import static by.training.carrent.model.dao.ColumnName.*;

import java.sql.Connection;
import java.sql.Date;
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
import by.training.carrent.model.dao.UserDao;
import by.training.carrent.model.entity.User;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final UserDaoImpl instance = new UserDaoImpl();
	private static final String SPASE = " ";
	private static final String UNDERSCORE = "_";
	private static final String SQL_CREATE_USER = """
			INSERT INTO users (email, password, password_for_authentication, first_name, last_name, discount,
			phone_number, date_of_birth, user_status_id, user_role_id)
			VALUES
			(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_FIND_BY_EMAIL = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 WHERE email = ?;
			""";
	private static final String SQL_FIND_BY_PASSWORD_FOR_AUTHENTICATION = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 WHERE password_for_authentication = ?;
			""";
	private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 WHERE email= ? AND password = ?;
			""";
	private static final String SQL_FIND_USERS_BY_LIMIT = """
			SELECT user_id, email, first_name, last_name, discount, phone_number,
			 date_of_birth, user_status.user_status, user_role.role
			 FROM users
			 JOIN user_status ON users.user_status_id = user_status.user_status_id
			 JOIN user_role ON users.user_role_id = user_role.role_id
			 LIMIT ?, ?
			""";
	private static final String SQL_UPDATE_PASSWORD_FOR_AUTHENTICATION = "UPDATE users SET password_for_authentication = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_FIRST_NAME = "UPDATE users SET first_name = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_LAST_NAME = "UPDATE users SET last_name = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_EMAIL = "UPDATE users SET email = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_DISCOUNT = "UPDATE users SET discount = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_PHONE_NUMBER = "UPDATE users SET phone_number = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";
	private static final String SQL_UPDATE_STATUS = "UPDATE users SET user_status_id = ? WHERE user_id = ?";
	private static final String SQL_UPDATE_ROLE = "UPDATE users SET user_role_id = ? WHERE user_id = ?";
	private static final String SQL_COUNT_USERS = "SELECT COUNT(user_id) AS count_users FROM users";
	
	private UserDaoImpl() {
	}

	public static UserDaoImpl getInstance() {
		return instance;
	}

	@Override
	public boolean add(User user, String password, String passwordForAuthentication) throws DaoException {
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, password);
			statement.setString(3, passwordForAuthentication);
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setInt(6, user.getDiscount());
			statement.setString(7, user.getPhoneNumber());
			statement.setDate(8, Date.valueOf(user.getDateOfBirth()));
			statement.setLong(9, user.getStatus().ordinal() + 1);
			statement.setLong(10, user.getRole().ordinal() + 1);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method add(): ", e);
			throw new DaoException("Exception when add user: {}", e);
		}
		return result;
	}

	@Override
	public Optional<User> findByEmail(String email) throws DaoException {
		logger.log(Level.INFO, "method findByEmail()");
		Optional<User> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				result = createOptionalUser(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByEmail()", e);
			throw new DaoException("Exception when find user by id", e);
		}
		return result;
	}

	@Override
	public Optional<User> findByPasswordForAuthentication(String passwordForAuthentication) throws DaoException {
		logger.log(Level.INFO, "method findByPasswordForAuthentication()");
		Optional<User> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_PASSWORD_FOR_AUTHENTICATION)) {
			statement.setString(1, passwordForAuthentication);
			try (ResultSet resultSet = statement.executeQuery()) {
				result = createOptionalUser(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByPasswordForAuthentication()", e);
			throw new DaoException("Exception when find user by password for authentication", e);
		}
		return result;
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
		logger.log(Level.INFO, "method findByEmailAndPassword()");
		Optional<User> result = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL_AND_PASSWORD)) {
			statement.setString(1, email);
			statement.setString(2, password);
			try (ResultSet resultSet = statement.executeQuery()) {
				result = createOptionalUser(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByEmailAndPassword()", e);
			throw new DaoException("Exception when find user by email and password", e);
		}
		return result;
	}
	
	@Override
	public List<User> findByLimit(int leftBorder, int numberOfLines) throws DaoException {
		logger.log(Level.INFO, "method findByLimit()");
		List<User> listUsers = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_LIMIT)) {
			statement.setInt(1, leftBorder);
			statement.setInt(2, numberOfLines);
			try (ResultSet resultSet = statement.executeQuery()) {
				listUsers = createListUsers(resultSet);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method findByLimit()", e);
			throw new DaoException("Exception when find users by limit", e);
		}
		return listUsers;
	}

	@Override
	public boolean updatePasswordForAuthentication(long userId, String passwordForAuthentication) throws DaoException {
		logger.log(Level.INFO, "method updatePasswordForAuthentication()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_FOR_AUTHENTICATION)) {
			statement.setLong(2, userId);
			statement.setString(1, passwordForAuthentication);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updatePasswordForAuthentication()", e);
			throw new DaoException("Exception when update password for authentication", e);
		}
		return result;
	}

	@Override
	public boolean updateFirstName(long userId, String name) throws DaoException {
		logger.log(Level.INFO, "method updateFirstName()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FIRST_NAME)) {
			statement.setLong(2, userId);
			statement.setString(1, name);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateFirstName()", e);
			throw new DaoException("Exception when update first name", e);
		}
		return result;
	}

	@Override
	public boolean updateLastName(long userId, String name) throws DaoException {
		logger.log(Level.INFO, "method updateLastName()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_LAST_NAME)) {
			statement.setLong(2, userId);
			statement.setString(1, name);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateLastName()", e);
			throw new DaoException("Exception when update last name", e);
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
			logger.log(Level.ERROR, "exception in method updateEmail()", e);
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
			logger.log(Level.ERROR, "exception in method updateDiscount()", e);
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
			logger.log(Level.ERROR, "exception in method updatePhoneNumber()", e);
			throw new DaoException("Exception when update phone number", e);
		}
		return result;
	}

	@Override
	public boolean updatePassword(String email, String password) throws DaoException {
		logger.log(Level.INFO, "method updatePassword()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
			statement.setString(2, email);
			statement.setString(1, password);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updatePassword()", e);
			throw new DaoException("Exception when update password", e);
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
			logger.log(Level.ERROR, "exception in method updateStatus()", e);
			throw new DaoException("Exception when update status", e);
		}
		return result;
	}
	

	@Override
	public boolean updateRole(long userId, long userRoleId) throws DaoException {
		logger.log(Level.INFO, "method updateRole()");
		boolean result = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
			statement.setLong(2, userId);
			statement.setLong(1, userRoleId);
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception in method updateRole()", e);
			throw new DaoException("Exception when update role", e);
		}
		return result;
	}
	
	@Override
	public int countUsers() throws DaoException  {
		logger.log(Level.INFO, "method countUsers()");
	int result = 0;
	try (Connection connection = ConnectionPool.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_COUNT_USERS)) {
		while (resultSet.next()) {
			result = resultSet.getInt(COUNT_USERS);
		}
	} catch (SQLException e) {
		logger.log(Level.ERROR, "exception in method countUsers()", e);
		throw new DaoException("Exception when count users", e);
	}
	return result;
	}

	private List<User> createListUsers(ResultSet resultSet) throws SQLException {
		logger.log(Level.INFO, "method createListUsers()");
		List<User> listUsers = new ArrayList<>();
		while (resultSet.next()) {
			User user = new User.Builder().setUserId(resultSet.getLong(USER_ID))
					.setEmail(resultSet.getString(USER_EMAIL)).setFirstName(resultSet.getString(USER_FIRST_NAME))
					.setLastName(resultSet.getString(USER_LAST_NAME)).setDiscount(resultSet.getInt(USER_DISCOUNT))
					.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER))
					.setDateOfBirth(resultSet.getDate(USER_DATE_OF_BIRTH).toLocalDate())
					.setStatus(User.UserStatus.valueOf(resultSet.getString(8).replace(SPASE, UNDERSCORE)))
					.setRole(User.UserRole.valueOf(resultSet.getString(9))).build();
			listUsers.add(user);
		}
		return listUsers;
	}
	
	private Optional<User> createOptionalUser(ResultSet resultSet) throws SQLException {
		logger.log(Level.INFO, "method createOptionalUser()");
		Optional<User> result = Optional.empty();
		while (resultSet.next()) {
			User user = new User.Builder().setUserId(resultSet.getLong(USER_ID))
					.setEmail(resultSet.getString(USER_EMAIL))
					.setFirstName(resultSet.getString(USER_FIRST_NAME))
					.setLastName(resultSet.getString(USER_LAST_NAME))
					.setDiscount(resultSet.getInt(USER_DISCOUNT))
					.setPhoneNumber(resultSet.getString(USER_PHONE_NUMBER))
					.setDateOfBirth(resultSet.getDate(USER_DATE_OF_BIRTH).toLocalDate())
					.setStatus(User.UserStatus.valueOf(resultSet.getString(8).replace(SPASE, UNDERSCORE)))
					.setRole(User.UserRole.valueOf(resultSet.getString(9))).build();
			result = Optional.ofNullable(user);
		}
		return result;
	}
}
