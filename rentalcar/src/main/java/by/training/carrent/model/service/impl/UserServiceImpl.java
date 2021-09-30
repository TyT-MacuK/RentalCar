package by.training.carrent.model.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.UserDaoImpl;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.UserService;
import by.training.carrent.model.validator.InputDataValidator;
import by.training.carrent.util.HashGenerator;

import static by.training.carrent.controller.command.RequestParameter.*;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private static final UserServiceImpl instance = new UserServiceImpl();
	private final UserDaoImpl userDao = UserDaoImpl.getInstance();

	private UserServiceImpl() {
	}

	public static UserServiceImpl getInstance() {
		return instance;
	}

	@Override
	public boolean registerUser(Map<String, String> parametrs) throws ServiceException {
		boolean result = false;
		logger.log(Level.INFO, "method registerUser()");
		String email = parametrs.get(USER_EMAIL);
		String password = parametrs.get(USER_PASSWORD);
		String passwordForAuthentication = parametrs.get(USER_PASSWORD_FOR_AUTHENTICATION);
		String firstName = parametrs.get(USER_FIRST_NAME);
		String lastName = parametrs.get(USER_LAST_NAME);
		String dateOfBirth = parametrs.get(USER_DATE_OF_BIRTH);
		String phoneNumber = parametrs.get(USER_PHONE_NUMBER);
		InputDataValidator validator = InputDataValidator.getInstance();

		if (validator.isEmailValid(email) && validator.isPasswordValid(password) && validator.isNameValid(firstName)
				&& validator.isNameValid(lastName) && validator.isPhoneNumberValid(phoneNumber)) {

			HashGenerator hashGenerator = HashGenerator.getInstance();
			String hashPassword = hashGenerator.generatePasswordHash(parametrs.get(USER_PASSWORD));
			User user = new User.Builder().setEmail(email).setFirstName(firstName).setLastName(lastName)
					.setDateOfBirth(LocalDate.parse(dateOfBirth)).setPhoneNumber(phoneNumber)
					.setStatus(User.UserStatus.CONFIRMATION_AWAITING).setRole(User.UserRole.USER).build();
			try {
				result = userDao.add(user, hashPassword, passwordForAuthentication);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "exception in method registerUser()", e);
				throw new ServiceException("Exception when registration user", e);
			}
		}
		return result;

	}

	@Override
	public Optional<User> findByEmail(String email) throws ServiceException {
		logger.log(Level.INFO, "method findByEmail()");
		try {
			return userDao.findByEmail(email);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByEmail()", e);
			throw new ServiceException("Exception when find user by email", e);
		}
	}

	@Override
	public Optional<User> findByPasswordForAuthentication(String passwordForAuthentication) throws ServiceException {
		logger.log(Level.INFO, "method findByPasswordForAuthentication()");
		try {
			return userDao.findByPasswordForAuthentication(passwordForAuthentication);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByPasswordForAuthentication()", e);
			throw new ServiceException("Exception when find user by password for authentication", e);
		}
	}

	@Override
	public List<User> findAll() throws ServiceException {
		logger.log(Level.INFO, "method findAll()");
		try {
			return userDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findAll()", e);
			throw new ServiceException("Exception when find all users", e);
		}
	}

	@Override
	public Optional<User> findById(long userId) throws ServiceException {
		logger.log(Level.INFO, "method findById()");
		try {
			return userDao.findById(userId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findById()", e);
			throw new ServiceException("Exception when find user by id", e);
		}
	}

	@Override
	public List<User> findByDateOfBirth(LocalDate dateOfBirth) throws ServiceException {
		logger.log(Level.INFO, "method findByDateOfBirth()");
		try {
			return userDao.findByDateOfBirth(dateOfBirth);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByDateOfBirth()", e);
			throw new ServiceException("Exception when find user by date of birth", e);
		}
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException {
		logger.log(Level.INFO, "method findByEmailAndPassword()");
		Optional<User> result = Optional.empty();
		InputDataValidator validator = InputDataValidator.getInstance();
		if (validator.isEmailValid(email) && validator.isPasswordValid(password)) {
			HashGenerator hashGenerator = HashGenerator.getInstance();
			password = hashGenerator.generatePasswordHash(password);
			try {
				result = userDao.findByEmailAndPassword(email, password);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "exception in method findByEmailAndPassword()", e);
				throw new ServiceException("Exception when find user by email and password", e);
			}
		}
		return result;
	}

	@Override
	public boolean updatePasswordForAuthentication(long userId, String passwordForAuthentication)
			throws ServiceException {
		logger.log(Level.INFO, "method removePasswordForAuthentication()");
		try {
			return userDao.updatePasswordForAuthentication(userId, passwordForAuthentication);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method removePasswordForAuthentication()", e);
			throw new ServiceException("Exception when remove password for authentication", e);
		}
	}

	@Override
	public boolean updateFirstName(long userId, String name) throws ServiceException {
		logger.log(Level.INFO, "method updateFirstName()");
		boolean result = false;
		try {
			InputDataValidator validator = InputDataValidator.getInstance();
			if (validator.isNameValid(name)) {
				result = userDao.updateFirstName(userId, name);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateFirstName()", e);
			throw new ServiceException("Exception when update first name", e);
		}
		return result;
	}

	@Override
	public boolean updateLastName(long userId, String name) throws ServiceException {
		logger.log(Level.INFO, "method updateLastName()");
		boolean result = false;
		try {
			InputDataValidator validator = InputDataValidator.getInstance();
			if (validator.isNameValid(name)) {
				result = userDao.updateFirstName(userId, name);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateLastName()", e);
			throw new ServiceException("Exception when update last name", e);
		}
		return result;
	}

	@Override
	public boolean updateEmail(long userId, String email, String password) throws ServiceException {
		logger.log(Level.INFO, "method updateEmail()");
		boolean result = false;
		try {
			InputDataValidator validator = InputDataValidator.getInstance();
			if (validator.isEmailValid(email) && validator.isPasswordValid(password)) {
				Optional<User> user = findByEmailAndPassword(email, password);
				if (user.isPresent()) {
					result = userDao.updateEmail(userId, email);
				}
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateEmail()", e);
			throw new ServiceException("Exception when update email", e);
		}
		return result;
	}

	@Override
	public boolean updateDiscount(long userId, int discount) throws ServiceException {
		logger.log(Level.INFO, "method updateDiscount()");
		try {
			return userDao.updateDiscount(userId, discount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateDiscount()", e);
			throw new ServiceException("Exception when update discount", e);
		}
	}

	@Override
	public boolean updatePhoneNumber(long userId, String phoneNumber) throws ServiceException {
		logger.log(Level.INFO, "method updatePhoneNumber()");
		boolean result = false;
		try {
			InputDataValidator validator = InputDataValidator.getInstance();
			if (validator.isPhoneNumberValid(phoneNumber)) {
				result = userDao.updatePhoneNumber(userId, phoneNumber);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updatePhoneNumber()", e);
			throw new ServiceException("Exception when update phone number", e);
		}
		return result;
	}

	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword) throws ServiceException {
		logger.log(Level.INFO, "method updateEmail()");
		boolean result = false;
		try {
			InputDataValidator validator = InputDataValidator.getInstance();
			if (validator.isPasswordValid(oldPassword) && validator.isPasswordValid(newPassword)) {
				Optional<User> user = findByEmailAndPassword(email, oldPassword);
				if (user.isPresent()) {
					HashGenerator hashGenerator = HashGenerator.getInstance();
					String hashPassword = hashGenerator.generatePasswordHash(newPassword);
					result = userDao.updatePassword(email, hashPassword);
				}
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updatePassword()", e);
			throw new ServiceException("Exception when update password", e);
		}
		return result;
	}

	@Override
	public boolean updateStatus(long userId, long statusId) throws ServiceException {
		logger.log(Level.INFO, "method updateStatus()");
		try {
			return userDao.updateStatus(userId, statusId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()", e);
			throw new ServiceException("Exception when update status", e);
		}
	}
}
