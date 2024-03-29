package by.training.carrent.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.CommandProvider;

import static by.training.carrent.controller.command.PagePath.*;
import static by.training.carrent.controller.command.RequestParameter.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = -4568529578476268214L;
	private static final Logger logger = LogManager.getLogger();
	private final CommandProvider provider = CommandProvider.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.log(Level.INFO, "method processRequest()");
		String commandName = request.getParameter(COMMAND);
		Command command = provider.getCommand(commandName);
		Router router = command.execute(request);
		switch (router.getType()) {
		case REDIRECT -> response.sendRedirect(router.getPagePath());
		case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
		default -> {
			logger.log(Level.ERROR, "unknown router type: {}", router.getType());
			response.sendRedirect(ERROR_404_PAGE);
		}
		}
	}
}
