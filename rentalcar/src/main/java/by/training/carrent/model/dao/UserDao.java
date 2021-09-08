package by.training.carrent.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.User;

public interface UserDao extends BaseDao<Long, User> {// TODO role update?
	List<User> findByDateOfBirth(LocalDate dateOfBirth) throws DaoException;
	
	Optional<User> findByEmail(String email) throws DaoException;
	
	Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

	boolean updateEmail(long userId, String email) throws DaoException;

	boolean updateDiscount(long userId, int discount) throws DaoException;

	boolean updatePhoneNumber(long userId, String phoneNumber) throws DaoException;

	boolean updateStatus(long userId, long userStatusId) throws DaoException;
}
