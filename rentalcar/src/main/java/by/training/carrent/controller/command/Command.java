package by.training.carrent.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.training.carrent.controller.Router;

/**
 * The Interface Command.
 */
public interface Command {

	/**
	 * Execute data and make router.
	 *
	 * @param request the request
	 * @return the router
	 */
	Router execute(HttpServletRequest request);
}
