package by.training.carrent.controller.command.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PageUrl;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.dao.ColumnName;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;
import by.training.carrent.util.EmailSender;
import by.training.carrent.util.RegisterCodeGenerator;
import by.training.carrent.controller.Router;

import static by.training.carrent.controller.command.RequestParameter.*;

public class SignUpPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Map<String, String> parameters = new HashMap<>();
		Router router;
		UserServiceImpl service = UserServiceImpl.getInstance();
		parameters.put(ColumnName.USER_EMAIL, request.getParameter(USER_EMAIL));
		parameters.put(ColumnName.USER_PASSWORD, request.getParameter(USER_PASSWORD));
		parameters.put(ColumnName.USER_FIRST_NAME, request.getParameter(USER_FIRST_NAME));
		parameters.put(ColumnName.USER_LAST_NAME, request.getParameter(USER_LAST_NAME));
		parameters.put(ColumnName.USER_DATE_OF_BIRTH, request.getParameter(USER_DATE_OF_BIRTH));
		parameters.put(ColumnName.USER_PHONE_NUMBER, request.getParameter(USER_PHONE_NUMBER));
		try {
			Optional<User> user = service.findByEmail(parameters.get(ColumnName.USER_EMAIL));
			if (!user.isPresent()) {		
				if (service.registerUser(parameters)) {
					RegisterCodeGenerator codeGenerator = RegisterCodeGenerator.getInstance();
					String code = codeGenerator.generateCode();
					request.getSession().setAttribute(SessionAttribute.GENERATE_CODE, code);
					request.getSession().setAttribute(SessionAttribute.USER_EMAIL, parameters.get(ColumnName.USER_EMAIL));
					EmailSender emailSender = EmailSender.getInstance(); 
					emailSender.sendMail(parameters.get(ColumnName.USER_EMAIL), code);
					router = new Router(Router.RouterType.FORWARD, PageUrl.CODE_PAGE);
					logger.log(Level.INFO, "user was registered but do not enter a code");
				} else {
					logger.log(Level.ERROR, "error during registration user");
					router = new Router(Router.RouterType.FORWARD, PageUrl.ERROR_500_PAGE);
				}
			} else {
				logger.log(Level.INFO, "user with this email was registered");
				router = new Router(Router.RouterType.FORWARD, PageUrl.DEFAULT_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error during registration user: ", e);
			router = new Router(Router.RouterType.FORWARD, PageUrl.ERROR_500_PAGE);
		}
		return router;
	}
}
