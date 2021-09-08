package by.training.carrent.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO default page

public class ActionFactory {
	private static final Logger logger = LogManager.getLogger();
	private static final String COMMAND = "command";
	
	public ActionCommand defineCommand(HttpServletRequest request) {
		logger.log(Level.INFO, "method defineCommand()");
		String action = request.getParameter(COMMAND);
		if (action == null || action.isBlank()) { //TODO default page
			logger.log(Level.ERROR, "actoin is empty");
		}
		CommandType commandType = CommandType.valueOf(action.toUpperCase());
		return commandType.getCommand();
	}
}
