package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.User;

/**
 * The Interface UserDao.
 */
public interface UserDao extends BaseDao<Long, User> {
	
	/**
	 * Add the user.
	 *
	 * @param user the user
	 * @param password the password
	 * @param passwordForAuthentication the password for authentication
	 * @return true, if user was added
	 * @throws DaoException the dao exception
	 */
	boolean add(User user, String password, String passwordForAuthentication) throws DaoException;
	
	/**
	 * Find user by password for authentication.
	 *
	 * @param passwordForAuthentication the password for authentication
	 * @return the optional user
	 * @throws DaoException the dao exception
	 */
	Optional<User> findByPasswordForAuthentication(String passwordForAuthentication) throws DaoException;
		
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the optional user
	 * @throws DaoException the dao exception
	 */
	Optional<User> findByEmail(String email) throws DaoException;
	
	/**
	 * Find user by email and password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the optional user
	 * @throws DaoException the dao exception
	 */
	Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
	
	/**
	 * Find users by limit.
	 *
	 * @param leftBorder the left border
	 * @param numberOfLines the number of lines
	 * @return the list of users
	 * @throws DaoException the dao exception
	 */
	List<User> findByLimit(int leftBorder, int numberOfLines) throws DaoException;
	
	/**
	 * Update password for authentication.
	 *
	 * @param userId the user id
	 * @param passwordForAuthentication the password for authentication
	 * @return true, if password for authentication was updated
	 * @throws DaoException the dao exception
	 */
	boolean updatePasswordForAuthentication(long userId, String passwordForAuthentication) throws DaoException;
	
	/**
	 * Update first name.
	 *
	 * @param userId the user id
	 * @param name the name
	 * @return true, if first name was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateFirstName(long userId, String name) throws DaoException;
	
	/**
	 * Update last name.
	 *
	 * @param userId the user id
	 * @param name the name
	 * @return true, if last name was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateLastName(long userId, String name) throws DaoException;

	/**
	 * Update email.
	 *
	 * @param userId the user id
	 * @param email the email
	 * @return true, if email was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateEmail(long userId, String email) throws DaoException;

	/**
	 * Update discount.
	 *
	 * @param userId the user id
	 * @param discount the discount
	 * @return true, if discount was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateDiscount(long userId, int discount) throws DaoException;

	/**
	 * Update phone number.
	 *
	 * @param userId the user id
	 * @param phoneNumber the phone number
	 * @return true, if phone number was updated
	 * @throws DaoException the dao exception
	 */
	boolean updatePhoneNumber(long userId, String phoneNumber) throws DaoException;
	
	/**
	 * Update password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return true, if password was updated
	 * @throws DaoException the dao exception
	 */
	boolean updatePassword(String email, String password) throws DaoException;

	/**
	 * Update status.
	 *
	 * @param userId the user id
	 * @param userStatusId the user status id
	 * @return true, if status was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateStatus(long userId, long userStatusId) throws DaoException;
	
	/**
	 * Update role.
	 *
	 * @param userId the user id
	 * @param userRoleId the user role id
	 * @return true, if role was updated
	 * @throws DaoException the dao exception
	 */
	boolean updateRole(long userId, long userRoleId) throws DaoException;
	
	/**
	 * Count users.
	 *
	 * @return the amount of users
	 * @throws DaoException the dao exception
	 */
	int countUsers() throws DaoException;
}
