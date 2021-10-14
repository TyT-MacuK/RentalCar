package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.User;

public interface UserDao extends BaseDao<Long, User> {
	boolean add(User user, String password, String passwordForAuthentication) throws DaoException;
	
	Optional<User> findByPasswordForAuthentication(String passwordForAuthentication) throws DaoException;
		
	Optional<User> findByEmail(String email) throws DaoException;
	
	Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
	
	List<User> findByLimit(int leftBorder, int numberOfLines) throws DaoException;
	
	boolean updatePasswordForAuthentication(long userId, String passwordForAuthentication) throws DaoException;
	
	boolean updateFirstName(long userId, String name) throws DaoException;
	
	boolean updateLastName(long userId, String name) throws DaoException;

	boolean updateEmail(long userId, String email) throws DaoException;

	boolean updateDiscount(long userId, int discount) throws DaoException;

	boolean updatePhoneNumber(long userId, String phoneNumber) throws DaoException;
	
	boolean updatePassword(String email, String password) throws DaoException;

	boolean updateStatus(long userId, long userStatusId) throws DaoException;
	
	int countUsers() throws DaoException;
}
