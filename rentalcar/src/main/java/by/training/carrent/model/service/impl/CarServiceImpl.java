package by.training.carrent.model.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.CarDaoImpl;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.service.CarService;

public class CarServiceImpl implements CarService {
	private static final Logger logger = LogManager.getLogger();
	private static final CarServiceImpl INSTANCE = new CarServiceImpl();
	private static final CarDaoImpl carDao = CarDaoImpl.getInstance();
	
	private CarServiceImpl() {
	}
	
	public CarServiceImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Car> findAll() throws ServiceException {
		logger.log(Level.INFO, "method findAll()");
		try {
			return carDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findAll()");
			throw new ServiceException("Exception when find all cars", e);
		}
	}

	@Override
	public Optional<Car> findById(long carId) throws ServiceException {
		logger.log(Level.INFO, "method findById()");
		try {
			return carDao.findById(carId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findById()");
			throw new ServiceException("Exception when find car by id", e);
		}
	}

	@Override
	public List<Car> findByModel(String model) throws ServiceException {
		logger.log(Level.INFO, "method findByModel()");
		try {
			return carDao.findByModel(model);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByModel()");
			throw new ServiceException("Exception when find car by model", e);
		}
	}

	@Override
	public List<Car> findByDiscount(int discount) throws ServiceException {
		logger.log(Level.INFO, "method findByDiscount()");
		try {
			return carDao.findByDiscount(discount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByDiscount()");
			throw new ServiceException("Exception when find car by discount", e);
		}
	}

	@Override
	public List<Car> findByYear(int year) throws ServiceException {
		logger.log(Level.INFO, "method findByYear()");
		try {
			return carDao.findByYear(year);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByYear()");
			throw new ServiceException("Exception when find car by year", e);
		}
	}

	@Override
	public List<Car> findByConditioner(boolean conditioner) throws ServiceException {
		logger.log(Level.INFO, "method findByConditioner()");
		try {
			return carDao.findByConditioner(conditioner);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByConditioner()");
			throw new ServiceException("Exception when find car by conditioner", e);
		}
	}

	@Override
	public List<Car> findByCost(BigDecimal cost) throws ServiceException {
		logger.log(Level.INFO, "method findByCost()");
		try {
			return carDao.findByCost(cost);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByCost()");
			throw new ServiceException("Exception when find car by cost", e);
		}
	}

	@Override
	public List<Car> findByClass(String carClass) throws ServiceException {
		logger.log(Level.INFO, "method findByClass()");
		try {
			return carDao.findByClass(carClass);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByClass()");
			throw new ServiceException("Exception when find car by class", e);
		}
	}

	@Override
	public List<Car> findByTransmission(String carTransmission) throws ServiceException {
		logger.log(Level.INFO, "method findByTransmission()");
		try {
			return carDao.findByTransmission(carTransmission);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByTransmission()");
			throw new ServiceException("Exception when find car by transmission", e);
		}
	}

	@Override
	public List<Car> findByManufacture(String carManufacturer) throws ServiceException {
		logger.log(Level.INFO, "method findByManufacture()");
		try {
			return carDao.findByManufacture(carManufacturer);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByManufacture()");
			throw new ServiceException("Exception when find car by manufacture", e);
		}
	}

	@Override
	public List<Car> findByStatus(String carStatus) throws ServiceException {
		logger.log(Level.INFO, "method findByStatus()");
		try {
			return carDao.findByStatus(carStatus);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByStatus()");
			throw new ServiceException("Exception when find car by status", e);
		}
	}

	@Override
	public boolean updateDiscount(long carId, int discount) throws ServiceException {
		logger.log(Level.INFO, "method updateDiscount()");
		try {
			return carDao.updateDiscount(carId, discount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateDiscount()");
			throw new ServiceException("Exception when update discount", e);
		}
	}

	@Override
	public boolean updateCost(long carId, BigDecimal cost) throws ServiceException {
		logger.log(Level.INFO, "method updateCost()");
		try {
			return carDao.updateCost(carId, cost);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateCost()");
			throw new ServiceException("Exception when update cost", e);
		}
	}

	@Override
	public boolean updateStatus(long carId, long carStatusId) throws ServiceException {
		logger.log(Level.INFO, "method updateStatus()");
		try {
			return carDao.updateStatus(carId, carStatusId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()");
			throw new ServiceException("Exception when update status", e);
		}
	}
}
