package by.training.carrent.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Part;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;

public interface CarService {
	
	boolean add(Map<String, String> parametrs, Part part) throws ServiceException;
	
	Optional<Car> findById(long carId) throws ServiceException;
	
	List<Car> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;

	List<Car> findByManufacture(String carManufacturer) throws ServiceException;
	
	Map<Long, Car> findCarsIdByUserId(List<Long> listCarsId) throws ServiceException;

	boolean updateDiscount(long carId, int discount) throws ServiceException;

	boolean updateCost(long carId, BigDecimal cost) throws ServiceException;

	boolean updateStatus(long carId, long carStatusId) throws ServiceException;
	
	void updateStatusOfCarsByListId(List<Long> carsId) throws ServiceException;
	
	int countCars() throws ServiceException;
}
