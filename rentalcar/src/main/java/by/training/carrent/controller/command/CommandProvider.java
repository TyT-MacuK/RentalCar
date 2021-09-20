package by.training.carrent.controller.command;

import java.util.EnumMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.impl.CodeEntryPageCommand;
import by.training.carrent.controller.command.impl.SignUpPageCommand;
import by.training.carrent.controller.command.impl.change.ChangeLanguageToEnglishCommand;
import by.training.carrent.controller.command.impl.change.ChangeLanguageToRussianCommand;
import by.training.carrent.controller.command.impl.page.GoToCodeEnteryPageCommand;
import by.training.carrent.controller.command.impl.page.GoToErrorPageCommand;
import by.training.carrent.controller.command.impl.page.GoToHomePageCommand;
import by.training.carrent.controller.command.impl.page.GoToSignUpPageCommand;

import static by.training.carrent.controller.command.CommandType.*;

public class CommandProvider {
	private static final Logger logger = LogManager.getLogger();
	private static final CommandProvider INSTANCE = new CommandProvider();
	private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

	private CommandProvider() {
		commands.put(SIGN_UP_PAGE, new SignUpPageCommand());
		commands.put(TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
		commands.put(CODE_ENTRY_PAGE, new CodeEntryPageCommand());
		commands.put(TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CHANGE_LANGUAGE_TO_ENGLISH_COMMAND, new ChangeLanguageToEnglishCommand());
		commands.put(CHANGE_LANGUAGE_TO_RUSSIAN_COMMAND, new ChangeLanguageToRussianCommand());
		
		commands.put(TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
		commands.put(TO_CODE_ENTERY_PAGE_COMMAND, new GoToCodeEnteryPageCommand());
		
	}

	public static CommandProvider getInstance() {
		return INSTANCE;
	}

	public Command getCommand(String commandName) {
		if (commandName == null) {
			logger.log(Level.WARN, "command name is null. The command was assigned the default value");
			return commands.get(TO_ERROR_PAGE);
		}
		CommandType commandType;
		try {
			commandType = valueOf(commandName.toUpperCase());
			logger.log(Level.INFO, "command type - {}", commandType);
		} catch (IllegalArgumentException e) {
			commandType = TO_ERROR_PAGE;
			logger.log(Level.ERROR, "error! The command was assigned the default value. ", e);
		}
		return commands.get(commandType);

	}
}
