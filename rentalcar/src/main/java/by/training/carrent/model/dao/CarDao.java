package by.training.carrent.model.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Car;

public interface CarDao extends BaseDao<Long, Car> {
	
	List<Car> findByLimit(int leftBorder, int numberOfLines) throws DaoException;

	List<Car> findByModel(String model) throws DaoException;

	List<Car> findByDiscount(int discount) throws DaoException;

	List<Car> findByYear(int year) throws DaoException;

	List<Car> findByCost(BigDecimal cost) throws DaoException;

	List<Car> findByManufacture(String carManufacturer) throws DaoException;

	List<Car> findByStatus(String carStatus) throws DaoException;
	
	Map<Long, Car> findCarsIdByUserId(List<Long> listCarsId) throws DaoException;

	boolean updateDiscount(long carId, int discount) throws DaoException;

	boolean updateCost(long carId, BigDecimal cost) throws DaoException;

	boolean updateStatus(long carId, long carStatusId) throws DaoException;
	
	int countCars() throws DaoException;
}
