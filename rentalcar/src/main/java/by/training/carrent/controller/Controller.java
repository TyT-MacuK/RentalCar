package by.training.carrent.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
import by.training.carrent.model.connection.ConnectionPool;

import static by.training.carrent.controller.command.PageUrl.*;
import static by.training.carrent.controller.command.RequestParameter.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
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
		String commandName = request.getParameter(COMMAND);
		Command command = provider.getCommand(commandName);
		Router router = command.execute(request);
		switch (router.getType()) {
		case REDIRECT:
			response.sendRedirect(router.getPageUri());
			break;
		case FORWARD:
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPageUri());
			dispatcher.forward(request, response);
			break;
		default:
			logger.log(Level.ERROR, "unknown router type: {}", router.getType());
			response.sendRedirect(ERROR_404_PAGE);
		}
	}
}
