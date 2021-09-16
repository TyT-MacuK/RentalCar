package by.training.carrent.controller.command;

import java.util.EnumMap;

import by.training.carrent.controller.command.impl.CodeEntryCommand;
import by.training.carrent.controller.command.impl.DefaultCommand;
import by.training.carrent.controller.command.impl.SignUpPageCommand;

import static by.training.carrent.controller.command.CommandType.*;

public class CommandProvider {
	private static final CommandProvider INSTANCE = new CommandProvider();
	private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

	private CommandProvider() {
		commands.put(SIGN_UP_PAGE, new SignUpPageCommand());
		commands.put(CODE_ENTRY_PAGE, new CodeEntryCommand());
		commands.put(DEFAULT, new DefaultCommand());
	}

	public static CommandProvider getInstance() {
		return INSTANCE;
	}

	public Command getCommand(String commandName) {
		if (commandName == null) {
			return commands.get(DEFAULT);
		}
		CommandType commandType;
		try {
			commandType = valueOf(commandName.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandType = DEFAULT;
		}
		return commands.get(commandType);

	}
}
