package by.training.carrent.exception;

/**
 * The Class ServiceException.
 */
public class ServiceException extends Exception {
	
	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 */
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause the cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
