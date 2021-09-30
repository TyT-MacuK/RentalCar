package by.training.carrent.controller.command.impl.change;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.User;
import by.training.carrent.model.service.impl.UserServiceImpl;
import by.training.carrent.util.EmailSender;
import by.training.carrent.util.CodeGenerator;

public class ChangeEmailCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		Router router;
		HttpSession session = request.getSession();
		UserServiceImpl service = UserServiceImpl.getInstance();
		User user = (User) session.getAttribute(SessionAttribute.USER);
		String email = request.getParameter(RequestParameter.USER_EMAIL);
		String password = request.getParameter(RequestParameter.USER_PASSWORD);
		try {
			if (service.updateEmail(user.getUserId(), email, password)) {
				CodeGenerator codeGenerator = CodeGenerator.getInstance();
				String code = codeGenerator.generateCodeToRegistration();
				service.updateStatus(user.getUserId(), User.UserStatus.CONFIRMATION_AWAITING.ordinal() + 1);
				service.updatePasswordForAuthentication(user.getUserId(), code);
				user.setEmail(email);
				session.setAttribute(SessionAttribute.IS_AUTHENTICATED, false);
				EmailSender emailSender = EmailSender.getInstance();
				emailSender.sendMail(email, code);
				router = new Router(PagePath.HOME_PAGE_REDIRECT);
				router.setRedirect();
				logger.log(Level.INFO, "the email was changed successfully");
			} else {
				logger.log(Level.INFO, "entered data is incorrect");
				router = new Router(PagePath.CHANGE_EMAIL_PAGE);
				request.setAttribute(RequestParameter.CHANGE_ERROR, true);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "error during changing user email: ", e);
			router = new Router(PagePath.ERROR_500_PAGE);
		}
		return router;
	}

}
