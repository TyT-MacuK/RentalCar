package by.training.carrent.controller;

/**
 * The Class Router.
 */
public class Router {

	/**
	 * The Enum RouterType.
	 */
	public enum RouterType {
		FORWARD, REDIRECT
	}

	/** The type of command. */
	private RouterType type;

	/** The page path. */
	private String pagePath;

	/**
	 * Instantiates a new router. Default type of router is FORWARD.
	 *
	 * @param pagePath the page path
	 */
	public Router(String pagePath) {
		type = RouterType.FORWARD;
		this.pagePath = pagePath;
	}

	/**
	 * Get the type.
	 *
	 * @return the type
	 */
	public RouterType getType() {
		return type;
	}

	/**
	 * Set the type of router REDIRECT.
	 */
	public void setRedirect() {
		type = RouterType.REDIRECT;
	}

	/**
	 * Get the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * Set the page uri.
	 *
	 * @param pagePath the new page uri
	 */
	public void setPageUri(String pagePath) {
		this.pagePath = pagePath;
	}

}
