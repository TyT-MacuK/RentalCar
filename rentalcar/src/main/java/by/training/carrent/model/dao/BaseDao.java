package by.training.carrent.model.dao;

import java.util.List;
import java.util.Optional;

import by.training.carrent.exception.DaoException;
import by.training.carrent.model.entity.AbstractEntity;

public interface BaseDao<K, T extends AbstractEntity> {
	//List<T> findAll()  throws DaoException;
	Optional<T> findById(K id)  throws DaoException;
	boolean remove(T t)  throws DaoException;
}
