package by.training.carrent.model.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.Car;

/**
 * The Interface CarDao.
 */
public interface CarDao extends BaseDao<Long, Car> {

	/**
	 * Add the car.
	 *
	 * @param car the car
	 * @return true, if car was added
	 * @throws DaoException the dao exception
	 */
	boolean add(Car car) throws DaoException;

	/**
	 * Find car by id.
	 *
	 * @param carId the car id
	 * @return the optional car
	 * @throws DaoException the dao exception
	 */
	Optional<Car> findById(long carId) throws DaoException;

	/**
	 * Find car by limit.
	 *
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of cars
	 * @throws DaoException the dao exception
	 */
	List<Car> findByLimit(int leftBorder, int numberOfLines) throws DaoException;

	/**
	 * Find car by manufacture.
	 *
	 * @param carManufacturer the car manufacturer
	 * @return the list of cars
	 * @throws DaoException the dao exception
	 */
	List<Car> findByManufacture(String carManufacturer) throws DaoException;

	/**
	 * Find cars by cars id.
	 *
	 * @param listCarsId the list cars id
	 * @return the map of cars id and cars
	 * @throws DaoException the dao exception
	 */
	Map<Long, Car> findCarsByCarsId(List<Long> listCarsId) throws DaoException;

	/**
	 * Update discount.
	 *
	 * @param carId    the car id
	 * @param discount the discount
	 * @return true, if discount was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateDiscount(long carId, int discount) throws DaoException;

	/**
	 * Update cost.
	 *
	 * @param carId the car id
	 * @param cost  the cost
	 * @return true, if cost was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateCost(long carId, BigDecimal cost) throws DaoException;

	/**
	 * Update status.
	 *
	 * @param carId       the car id
	 * @param carStatusId the car status id
	 * @return true, if status was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateStatus(long carId, long carStatusId) throws DaoException;

	/**
	 * Update status of cars by list cars id.
	 *
	 * @param carsId the cars id
	 * @return true, if cost was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateStatusOfCarsByListId(List<Long> carsId) throws DaoException;

	/**
	 * Count cars.
	 *
	 * @return the amount of cars
	 * @throws DaoException the dao exception
	 */
	int countCars() throws DaoException;
}
