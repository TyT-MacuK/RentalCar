package by.training.carrent.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Part;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;

/**
 * The Interface CarService.
 */
public interface CarService {

	/**
	 * Add the car
	 *
	 * @param parameters the parameters of car
	 * @param part       the part of picture
	 * @return true, if car was added
	 * @throws ServiceException the service exception
	 */
	boolean add(Map<String, String> parameters, Part part) throws ServiceException;

	/**
	 * Find by id.
	 *
	 * @param carId the car id
	 * @return the optional car
	 * @throws ServiceException the service exception
	 */
	Optional<Car> findById(long carId) throws ServiceException;

	/**
	 * Find by limit.
	 *
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of cars
	 * @throws ServiceException the service exception
	 */
	List<Car> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;

	/**
	 * Find by manufacture.
	 *
	 * @param carManufacturer the car manufacturer
	 * @return the list of cars
	 * @throws ServiceException the service exception
	 */
	List<Car> findByManufacture(String carManufacturer) throws ServiceException;

	/**
	 * Find map of cars id by list of cars id.
	 *
	 * @param listCarsId the list cars id
	 * @return the map of cars id and cars
	 * @throws ServiceException the service exception
	 */
	Map<Long, Car> findCarsByCarsId(List<Long> listCarsId) throws ServiceException;

	/**
	 * Update discount.
	 *
	 * @param carId    the car id
	 * @param discount the discount
	 * @return true, if discount was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateDiscount(long carId, int discount) throws ServiceException;

	/**
	 * Update cost.
	 *
	 * @param carId the car id
	 * @param cost  the cost
	 * @return true, if cost was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateCost(long carId, BigDecimal cost) throws ServiceException;

	/**
	 * Update status.
	 *
	 * @param carId       the car id
	 * @param carStatusId the car status id
	 * @return true, if status was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateStatus(long carId, long carStatusId) throws ServiceException;

	/**
	 * Count cars.
	 *
	 * @return the amount of cars
	 * @throws ServiceException the service exception
	 */
	int countCars() throws ServiceException;
}
