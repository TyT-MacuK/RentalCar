package by.training.carrent.model.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.UserDaoImpl;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.UserService;
import by.training.carrent.util.HashGenerator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private static final UserServiceImpl INSTANCE = new UserServiceImpl();
	private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
	
	private UserServiceImpl() {
	}

	public static UserServiceImpl getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Optional<User> registration(User user, String password) throws ServiceException {
		logger.log(Level.INFO, "method registration()");
		Optional<User> registeredUser  = Optional.empty();
		HashGenerator hashGenerator = HashGenerator.getInstance();
		password = hashGenerator.generatePasswordHash(password);
		try {
			registeredUser = userDao.findByEmailAndPassword(user.getEmail(), password);
			if (registeredUser.isEmpty()) {
				boolean isRegistrationSuccess = userDao.add(user, password);
				if (isRegistrationSuccess) {
					registeredUser = userDao.findByEmail(user.getEmail());
				}
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method registration()", e);
			throw new ServiceException("Exception when registration user", e);
		}
		return registeredUser;
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
	public boolean updateEmail(long userId, String email) throws ServiceException {
		logger.log(Level.INFO, "method updateEmail()");
		try {
			return userDao.updateEmail(userId, email);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateEmail()", e);
			throw new ServiceException("Exception when update email", e);
		}
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
		try {
			return userDao.updatePhoneNumber(userId, phoneNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updatePhoneNumber()", e);
			throw new ServiceException("Exception when update phone number", e);
		}
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
