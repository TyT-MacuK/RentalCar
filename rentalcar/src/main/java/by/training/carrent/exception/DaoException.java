package by.training.carrent.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1760124705251219592L;

	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
