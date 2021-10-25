package by.training.carrent.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Register user.
	 *
	 * @param parameters the parameters
	 * @return true, if user was added
	 * @throws ServiceException the service exception
	 */
	boolean registerUser(Map<String, String> parameters) throws ServiceException;

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional user
	 * @throws ServiceException the service exception
	 */
	Optional<User> findByEmail(String email) throws ServiceException;

	/**
	 * Find by password for authentication.
	 *
	 * @param passwordForAuthentication the password for authentication
	 * @return the optional user
	 * @throws ServiceException the service exception
	 */
	Optional<User> findByPasswordForAuthentication(String passwordForAuthentication) throws ServiceException;

	/**
	 * Find by email and password.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the optional user
	 * @throws ServiceException the service exception
	 */
	Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException;

	/**
	 * Find by limit.
	 *
	 * @param leftBorder    the left border
	 * @param numberOfLines the number of lines
	 * @return the list of users
	 * @throws ServiceException the service exception
	 */
	List<User> findByLimit(int leftBorder, int numberOfLines) throws ServiceException;

	/**
	 * Update password for authentication.
	 *
	 * @param userId                    the user id
	 * @param passwordForAuthentication the password for authentication
	 * @return true, if password for authentication was updated
	 * @throws ServiceException the service exception
	 */
	boolean updatePasswordForAuthentication(long userId, String passwordForAuthentication) throws ServiceException;

	/**
	 * Update first name.
	 *
	 * @param userId the user id
	 * @param name   the name
	 * @return true, if first name was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateFirstName(long userId, String name) throws ServiceException;

	/**
	 * Update last name.
	 *
	 * @param userId the user id
	 * @param name   the name
	 * @return true, if last name was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateLastName(long userId, String name) throws ServiceException;

	/**
	 * Update email.
	 *
	 * @param userId   the user id
	 * @param email    the email
	 * @param password the password
	 * @return true, if email was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateEmail(long userId, String email, String password) throws ServiceException;

	/**
	 * Update discount.
	 *
	 * @param userId   the user id
	 * @param discount the discount
	 * @return true, if discount was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateDiscount(long userId, int discount) throws ServiceException;

	/**
	 * Update phone number.
	 *
	 * @param userId      the user id
	 * @param phoneNumber the phone number
	 * @return true, if phone number was updated
	 * @throws ServiceException the service exception
	 */
	boolean updatePhoneNumber(long userId, String phoneNumber) throws ServiceException;

	/**
	 * Update password.
	 *
	 * @param email       the email
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @return true, if password was updated
	 * @throws ServiceException the service exception
	 */
	boolean updatePassword(String email, String oldPassword, String newPassword) throws ServiceException;

	/**
	 * Update status.
	 *
	 * @param userId   the user id
	 * @param statusId the status id
	 * @return true, if status was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateStatus(long userId, long statusId) throws ServiceException;

	/**
	 * Update role.
	 *
	 * @param userId the user id
	 * @param roleId the role id
	 * @return true, if role was updated
	 * @throws ServiceException the service exception
	 */
	boolean updateRole(long userId, long roleId) throws ServiceException;

	/**
	 * Count users.
	 *
	 * @return the amount of users
	 * @throws ServiceException the service exception
	 */
	int countUsers() throws ServiceException;
}
