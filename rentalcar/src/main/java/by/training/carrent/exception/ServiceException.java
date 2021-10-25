package by.training.carrent.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -336897113765701694L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
