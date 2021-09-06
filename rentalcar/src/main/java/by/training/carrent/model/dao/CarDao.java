package by.training.carrent.model.dao;

import java.math.BigDecimal;
import java.util.List;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Car;

public interface CarDao extends BaseDao<Long, Car> {

	List<Car> findByModel(String model) throws DaoException;

	List<Car> findByDiscount(int discount) throws DaoException;

	List<Car> findByYear(int year) throws DaoException;

	List<Car> findByConditioner(boolean conditioner) throws DaoException;

	List<Car> findByCost(BigDecimal cost) throws DaoException;

	List<Car> findByClass(String carClass) throws DaoException;

	List<Car> findByTransmission(String carTransmission) throws DaoException;

	List<Car> findByManufacture(String carManufacturer) throws DaoException;

	List<Car> findByStatus(String carStatus) throws DaoException;

	boolean updateDiscount(long carId, int discount) throws DaoException;

	boolean updateCost(long carId, BigDecimal cost) throws DaoException;

	boolean updateStatus(long carId, long carStatusId) throws DaoException;
}
