package by.training.carrent.controller.command.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.Router;
import by.training.carrent.controller.command.Command;
import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.SessionAttribute;
import by.training.carrent.exception.ServiceException;
import by.training.carrent.model.entity.Car;
import by.training.carrent.model.service.UserService;
import by.training.carrent.model.service.impl.CarServiceImpl;
import by.training.carrent.model.service.impl.UserServiceImpl;

//TODO

public class GoToHomePageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String ENGLISH = "en";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "method execute()");
		HttpSession session = request.getSession();
		if (session.getAttribute(SessionAttribute.LOCALE) == null) {
			session.setAttribute(SessionAttribute.LOCALE, ENGLISH);
		}
		session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.HOME_PAGE_REDIRECT);
		CarServiceImpl service = CarServiceImpl.getInstance();
		List<Car> cars = new ArrayList<>();
		try {
			cars = service.findAll();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("cars", cars);
        logger.log(Level.INFO, "redirected to home page");
		return new Router(PagePath.HOME_PAGE);
	}

}
