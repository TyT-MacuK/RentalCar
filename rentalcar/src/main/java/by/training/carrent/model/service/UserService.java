package by.training.carrent.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;

public interface UserService {
	Optional<User> registration(User user, String password) throws ServiceException;
	
	List<User> findAll() throws ServiceException;

	Optional<User> findById(long userId) throws ServiceException;

	List<User> findByDateOfBirth(LocalDate dateOfBirth) throws ServiceException;

	boolean updateEmail(long userId, String email) throws ServiceException;

	boolean updateDiscount(long userId, int discount) throws ServiceException;

	boolean updatePhoneNumber(long userId, String phoneNumber) throws ServiceException;

	boolean updateStatus(long userId, long statusId) throws ServiceException;
}
