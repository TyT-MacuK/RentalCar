package by.training.carrent.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;

public interface CarService {
	List<Car> findAll() throws ServiceException;
	
	List<Car> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;
	
	Optional<Car> findById(long carId) throws ServiceException;
	
	List<Car> findByModel(String model) throws ServiceException;

	List<Car> findByDiscount(int discount) throws ServiceException;

	List<Car> findByYear(int year) throws ServiceException;

	List<Car> findByCost(BigDecimal cost) throws ServiceException;

	List<Car> findByManufacture(String carManufacturer) throws ServiceException;

	List<Car> findByStatus(String carStatus) throws ServiceException;

	boolean updateDiscount(long carId, int discount) throws ServiceException;

	boolean updateCost(long carId, BigDecimal cost) throws ServiceException;

	boolean updateStatus(long carId, long carStatusId) throws ServiceException;
	
	int countCars() throws ServiceException;
}
