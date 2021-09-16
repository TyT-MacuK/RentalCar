package by.training.carrent.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.training.carrent.controller.Router;

public interface Command {
	Router execute(HttpServletRequest request);
}
