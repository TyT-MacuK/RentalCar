package by.training.carrent.controller.command.impl;

import static by.training.carrent.controller.command.RequestParameter.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;
import by.training.carrent.util.EmailSender;
import by.training.carrent.util.CodeGenerator;
import by.training.carrent.controller.Router;

public class SignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Map<String, String> parameters = new HashMap<>();
		Router router;
		String dateOfBirth = request.getParameter(USER_DATE_OF_BIRTH);
		if (dateOfBirth != null && !dateOfBirth.isBlank()) {
			UserServiceImpl service = UserServiceImpl.getInstance();
			parameters.put(USER_EMAIL, request.getParameter(USER_EMAIL));
			parameters.put(USER_PASSWORD, request.getParameter(USER_PASSWORD));
			parameters.put(USER_FIRST_NAME, request.getParameter(USER_FIRST_NAME));
			parameters.put(USER_LAST_NAME, request.getParameter(USER_LAST_NAME));
			parameters.put(USER_DATE_OF_BIRTH, dateOfBirth);
			parameters.put(USER_PHONE_NUMBER, request.getParameter(USER_PHONE_NUMBER));
			CodeGenerator codeGenerator = CodeGenerator.getInstance();
			String hashPassword = codeGenerator.generateCodeToRegistration();
			parameters.put(USER_PASSWORD_FOR_AUTHENTICATION, hashPassword);
			try {
				Optional<User> user = service.findByEmail(parameters.get(USER_EMAIL));
				if (!user.isPresent()) {
					if (service.registerUser(parameters)) {
						EmailSender emailSender = EmailSender.getInstance();
						emailSender.sendMail(parameters.get(USER_EMAIL), hashPassword);
						router = new Router(PagePath.HOME_PAGE_REDIRECT);
						router.setRedirect();
						logger.log(Level.INFO, "user was registered but do not enter a code");
					} else {
						logger.log(Level.ERROR, "error during registration user");
						router = new Router(PagePath.ERROR_500_PAGE);
					}
				} else {
					logger.log(Level.INFO, "user with this email was registered");
					router = new Router(PagePath.SIGN_IN_PAGE);
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "error during registration user: ", e);
				router = new Router(PagePath.ERROR_500_PAGE);
			}
		} else {
			logger.log(Level.INFO, "user entered empty date");
			router = new Router(PagePath.SIGN_UP_PAGE);
		}
		return router;
	}
}