package by.training.carrent.command;

import javax.servlet.http.HttpServletRequest;

import by.training.carrent.controller.Router;

public interface ActionCommand {
	Router execute(HttpServletRequest request);
}
