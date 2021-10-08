package by.training.carrent.model.service.impl;

import static by.training.carrent.controller.command.RequestParameter.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.DaoException;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.impl.CarDaoImpl;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.service.CarService;
import by.training.carrent.model.validator.InputDataValidator;

public class CarServiceImpl implements CarService {
	private static final Logger logger = LogManager.getLogger();
	private static final CarServiceImpl instance = new CarServiceImpl();
	private static final String UPLOAD_DIR = "image";
	private final CarDaoImpl carDao = CarDaoImpl.getInstance();

	private CarServiceImpl() {
	}

	public static CarServiceImpl getInstance() {
		return instance;
	}

	@Override
	public boolean add(Map<String, String> parametrs, Part part) throws ServiceException {
		logger.log(Level.INFO, "method add()");
		boolean result = false;
		InputDataValidator validator = InputDataValidator.getInstance();
		String manufacturer = parametrs.get(CAR_MANUFACTURER);
		String model = parametrs.get(CAR_MODEL);
		String year = parametrs.get(CAR_YEAR);
		String conditioner = parametrs.get(CAR_CONDITIONER);
		String transmission = parametrs.get(CAR_TRANSMISSION);
		String cost = parametrs.get(CAR_COST);
		String discount = parametrs.get(CAR_DISCOUNT);
		String status = parametrs.get(CAR_STATUS);
		String imageUploadDir = parametrs.get(IMAGE_UPLOAD_DIRECTORY);
		if (validator.isCarDataValid(model, year, cost, discount)) {
			String imagePath = saveInageToDisk(part, imageUploadDir);
			Car car = new Car.Builder().setModel(model)
					.setCarManufacturer(Car.CarManufacturer.valueOf(manufacturer.toUpperCase()))
					.setYear(Integer.parseInt(year))
					.setConditioner(conditioner != null)
					.setImageUrl(imagePath)
					.setCarTransmission(Car.CarTransmission.valueOf(transmission.toUpperCase()))
					.setCost(new BigDecimal(cost))
					.setDiscount(Integer.parseInt(discount))
					.setCarStatus(Car.CarStatus.valueOf(status.toUpperCase()))
					.build();
			try {
				result = carDao.add(car);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "exception in method add()", e);
				throw new ServiceException("Exception when add car", e);
			}
		}
		return result;
	}

	@Override
	public List<Car> findByLimit(int leftBorder, int numberOfLines) throws ServiceException {
		logger.log(Level.INFO, "method findByLimit()");
		try {
			return carDao.findByLimit(leftBorder, numberOfLines);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByLimit()", e);
			throw new ServiceException("Exception when find cars by limit", e);
		}
	}

	@Override
	public Optional<Car> findById(long carId) throws ServiceException {
		logger.log(Level.INFO, "method findById()");
		try {
			return carDao.findById(carId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findById()", e);
			throw new ServiceException("Exception when find car by id", e);
		}
	}

	@Override
	public List<Car> findByModel(String model) throws ServiceException {
		logger.log(Level.INFO, "method findByModel()");
		try {
			return carDao.findByModel(model);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByModel()", e);
			throw new ServiceException("Exception when find car by model", e);
		}
	}

	@Override
	public List<Car> findByDiscount(int discount) throws ServiceException {
		logger.log(Level.INFO, "method findByDiscount()");
		try {
			return carDao.findByDiscount(discount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByDiscount()", e);
			throw new ServiceException("Exception when find car by discount", e);
		}
	}

	@Override
	public List<Car> findByYear(int year) throws ServiceException {
		logger.log(Level.INFO, "method findByYear()");
		try {
			return carDao.findByYear(year);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByYear()", e);
			throw new ServiceException("Exception when find car by year", e);
		}
	}

	@Override
	public List<Car> findByCost(BigDecimal cost) throws ServiceException {
		logger.log(Level.INFO, "method findByCost()");
		try {
			return carDao.findByCost(cost);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByCost()", e);
			throw new ServiceException("Exception when find car by cost", e);
		}
	}

	@Override
	public List<Car> findByManufacture(String carManufacturer) throws ServiceException {
		logger.log(Level.INFO, "method findByManufacture()");
		try {
			carManufacturer = carManufacturer.toUpperCase();
			return carDao.findByManufacture(carManufacturer);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByManufacture()", e);
			throw new ServiceException("Exception when find car by manufacture", e);
		}
	}

	@Override
	public List<Car> findByStatus(String carStatus) throws ServiceException {
		logger.log(Level.INFO, "method findByStatus()");
		try {
			return carDao.findByStatus(carStatus);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findByStatus()", e);
			throw new ServiceException("Exception when find car by status", e);
		}
	}
	
	@Override
	public Map<Long, Car> findCarsIdByUserId(List<Long> listCarsId) throws ServiceException {
		logger.log(Level.INFO, "method findCarsIdByUserId()");
		try {
			return carDao.findCarsIdByUserId(listCarsId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method findCarsIdByUserId()", e);
			throw new ServiceException("Exception when find cars id by user id", e);
		}
	}

	@Override
	public boolean updateDiscount(long carId, int discount) throws ServiceException {
		logger.log(Level.INFO, "method updateDiscount()");
		try {
			return carDao.updateDiscount(carId, discount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateDiscount()", e);
			throw new ServiceException("Exception when update discount", e);
		}
	}

	@Override
	public boolean updateCost(long carId, BigDecimal cost) throws ServiceException {
		logger.log(Level.INFO, "method updateCost()");
		try {
			return carDao.updateCost(carId, cost);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateCost()", e);
			throw new ServiceException("Exception when update cost", e);
		}
	}

	@Override
	public boolean updateStatus(long carId, long carStatusId) throws ServiceException {
		logger.log(Level.INFO, "method updateStatus()");
		try {
			return carDao.updateStatus(carId, carStatusId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method updateStatus()", e);
			throw new ServiceException("Exception when update status", e);
		}
	}

	@Override
	public int countCars() throws ServiceException {
		logger.log(Level.INFO, "method countCars()");
		try {
			return carDao.countCars();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "exception in method countCars()", e);
			throw new ServiceException("Exception when count cars", e);
		}
	}
	
	private String saveInageToDisk(Part part, String imageUploadDir) throws ServiceException {
		logger.log(Level.INFO, "method saveInageToDisk()");
		String imagePath = null;
		try (InputStream inputStream = part.getInputStream()) {
			String submittedName = part.getSubmittedFileName();
			Path path = new File(imageUploadDir.concat(submittedName)).toPath();
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
			imagePath = File.separator.concat(UPLOAD_DIR).concat(File.separator).concat(submittedName);
		} catch (IOException e) {
			logger.log(Level.ERROR, "exception in method saveInageToDisk()", e);
			throw new ServiceException("Exception when save image", e);
		}
		return imagePath;
	}
}
