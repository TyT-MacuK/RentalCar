package by.training.carrent.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.SessionAttribute;

public class ChangeLanguageCommand implements Command {
	private static final String ENGLISH = "en";
	private static final String RUSSIAN = "ru";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String language = session.getAttribute(SessionAttribute.LOCALE).toString();
		switch (language) {
		case RUSSIAN -> session.setAttribute(SessionAttribute.LOCALE, ENGLISH);
		case ENGLISH -> session.setAttribute(SessionAttribute.LOCALE, RUSSIAN);
		default -> session.setAttribute(SessionAttribute.LOCALE, ENGLISH);
		}
		return new Router(Router.RouterType.REDIRECT, session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
	}

}
