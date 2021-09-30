package by.training.carrent.controller.command;

import java.util.EnumMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.impl.CodeEntryCommand;
import by.training.carrent.controller.command.impl.MakeOrderCommand;
import by.training.carrent.controller.command.impl.PaymentCommand;
import by.training.carrent.controller.command.impl.SignInCommand;
import by.training.carrent.controller.command.impl.SignOutCommand;
import by.training.carrent.controller.command.impl.SignUpCommand;
import by.training.carrent.controller.command.impl.change.ChangeEmailCommand;
import by.training.carrent.controller.command.impl.change.ChangeFirstNameCommand;
import by.training.carrent.controller.command.impl.change.ChangeLanguageToEnglishCommand;
import by.training.carrent.controller.command.impl.change.ChangeLanguageToRussianCommand;
import by.training.carrent.controller.command.impl.change.ChangeLastNameCommand;
import by.training.carrent.controller.command.impl.change.ChangePasswordCommand;
import by.training.carrent.controller.command.impl.change.ChangePhoneNumberCommand;
import by.training.carrent.controller.command.impl.page.GoToCodeEnteryPageCommand;
import by.training.carrent.controller.command.impl.page.GoToErrorPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeEmailPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeFirstNamePageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeLastNamePageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangePasswordPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangePhoneNumberPageCommand;
import by.training.carrent.controller.command.impl.page.GoToHomePageCommand;
import by.training.carrent.controller.command.impl.page.GoToMakeOrderPageCommand;
import by.training.carrent.controller.command.impl.page.GoToPersonalProfilePageCommand;
import by.training.carrent.controller.command.impl.page.GoToSignInPageCommand;
import by.training.carrent.controller.command.impl.page.GoToSignUpPageCommand;

import static by.training.carrent.controller.command.CommandType.*;

public class CommandProvider {
	private static final Logger logger = LogManager.getLogger();
	private static final CommandProvider INSTANCE = new CommandProvider();
	private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

	private CommandProvider() {
		commands.put(TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
		commands.put(SIGN_UP_PAGE, new SignUpCommand());
		commands.put(TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
		commands.put(CODE_ENTRY_PAGE, new CodeEntryCommand());
		commands.put(TO_CODE_ENTERY_PAGE_COMMAND, new GoToCodeEnteryPageCommand());
		commands.put(SIGN_IN_PAGE, new SignInCommand());
		commands.put(TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
		commands.put(SIGN_OUT_COMMAND, new SignOutCommand());
		commands.put(TO_PERSONAL_PROFILE_PAGE_COMMAND, new GoToPersonalProfilePageCommand());
		commands.put(TO_MAKE_ORDER_PAGE_COMMAND, new GoToMakeOrderPageCommand());
		commands.put(MAKE_ORDER_PAGE, new MakeOrderCommand());
		commands.put(PAYMENT_ENTRY_PAGE, new PaymentCommand());
		commands.put(TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CHANGE_LANGUAGE_TO_ENGLISH_COMMAND, new ChangeLanguageToEnglishCommand());
		commands.put(CHANGE_LANGUAGE_TO_RUSSIAN_COMMAND, new ChangeLanguageToRussianCommand());	
		commands.put(TO_CHANGE_FIRST_NAME_PAGE_COMMAND, new GoToChangeFirstNamePageCommand());
		commands.put(CHANGE_FIRST_NAME_PAGE, new ChangeFirstNameCommand());
		commands.put(TO_CHANGE_LAST_NAME_PAGE_COMMAND, new GoToChangeLastNamePageCommand());
		commands.put(CHANGE_LAST_NAME_PAGE, new ChangeLastNameCommand());
		commands.put(TO_CHANGE_PHONE_NUMBER_PAGE_COMMAND, new GoToChangePhoneNumberPageCommand());
		commands.put(CHANGE_PHONE_NUMBER_PAGE, new ChangePhoneNumberCommand());
		commands.put(TO_CHANGE_EMAIL_PAGE_COMMAND, new GoToChangeEmailPageCommand());
		commands.put(CHANGE_EMAIL_PAGE, new ChangeEmailCommand());
		commands.put(TO_CHANGE_PASSWORD_PAGE_COMMAND, new GoToChangePasswordPageCommand());
		commands.put(CHANGE_PASSWORD_PAGE, new ChangePasswordCommand());
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
