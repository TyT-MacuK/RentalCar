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
import by.training.carrent.controller.command.impl.change.admin.ChangeUserStatusCommand;
import by.training.carrent.controller.command.impl.change.admin.ChangeCarCostCommand;
import by.training.carrent.controller.command.impl.change.admin.ChangeCarDiscountCommand;
import by.training.carrent.controller.command.impl.change.admin.ChangeCarStatusCommand;
import by.training.carrent.controller.command.impl.change.admin.ChangeOrderStatus;
import by.training.carrent.controller.command.impl.change.admin.ChangeUserDiscountCommand;
import by.training.carrent.controller.command.impl.change.admin.ChangeUserRoleCommand;
import by.training.carrent.controller.command.impl.find.FindCarByManufacturerCommand;
import by.training.carrent.controller.command.impl.find.admin.FindOrderByIdCommand;
import by.training.carrent.controller.command.impl.page.GoToCodeEntryPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeEmailPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeFirstNamePageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangeLastNamePageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangePasswordPageCommand;
import by.training.carrent.controller.command.impl.page.GoToChangePhoneNumberPageCommand;
import by.training.carrent.controller.command.impl.page.GoToHomePageCommand;
import by.training.carrent.controller.command.impl.page.GoToMakeOrderPageCommand;
import by.training.carrent.controller.command.impl.page.GoToOrdersPageCommand;
import by.training.carrent.controller.command.impl.page.GoToPaymentEntryPageCommand;
import by.training.carrent.controller.command.impl.page.GoToPersonalProfilePageCommand;
import by.training.carrent.controller.command.impl.page.GoToSignInPageCommand;
import by.training.carrent.controller.command.impl.page.GoToSignUpPageCommand;
import by.training.carrent.controller.command.impl.page.admin.AdminAddCarCommand;
import by.training.carrent.controller.command.impl.page.admin.GoToAdminAddCarPageCommand;
import by.training.carrent.controller.command.impl.page.admin.GoToAdminCarsPageCommand;
import by.training.carrent.controller.command.impl.page.admin.GoToAdminOrdersPageCommand;
import by.training.carrent.controller.command.impl.page.admin.GoToAdminUsersPageCommand;

import static by.training.carrent.controller.command.CommandType.*;

public final class CommandProvider {
	private static final Logger logger = LogManager.getLogger();
	private static final CommandProvider INSTANCE = new CommandProvider();
	private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

	private CommandProvider() {
		initializeCommonCommands();
		initializeAdminCommands();
		initializeUserCommands();
		initializeSignUpCommands();
		initializeCommonChangeCommands();
		initializeAdminChangeCommands();		
	}

	public static CommandProvider getInstance() {
		return INSTANCE;
	}

	public Command getCommand(String commandName) {
		if (commandName == null) {
			logger.log(Level.WARN, "command name is null. The command was assigned the home page");
			return commands.get(TO_HOME_PAGE_COMMAND);
		}
		CommandType commandType;
		try {
			commandType = valueOf(commandName.toUpperCase());
			logger.log(Level.INFO, "command type - {}", commandType);
		} catch (IllegalArgumentException e) {
			commandType = TO_HOME_PAGE_COMMAND;
			logger.log(Level.ERROR, "error! The command was assigned the home page. ", e);
		}
		return commands.get(commandType);
	}

	private void initializeCommonCommands() {
		commands.put(TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
		commands.put(SIGN_IN_PAGE, new SignInCommand());
		commands.put(TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
		commands.put(SIGN_OUT_COMMAND, new SignOutCommand());
		commands.put(TO_PERSONAL_PROFILE_PAGE_COMMAND, new GoToPersonalProfilePageCommand());
		commands.put(MAKE_ORDER_PAGE, new MakeOrderCommand());
		commands.put(TO_MAKE_ORDER_PAGE_COMMAND, new GoToMakeOrderPageCommand());
		commands.put(PAYMENT_ENTRY_PAGE, new PaymentCommand());
		commands.put(TO_PAYMENT_ENTRY_PAGE_COMMAND, new GoToPaymentEntryPageCommand());
		commands.put(FIND_MANUFACTURER_BY_ID_COMMAND, new FindCarByManufacturerCommand());
	}

	private void initializeAdminCommands() {
		commands.put(TO_ADMIN_USERS_PAGE_COMMAND, new GoToAdminUsersPageCommand());
		commands.put(TO_ADMIN_ORDERS_PAGE_COMMAND, new GoToAdminOrdersPageCommand());
		commands.put(TO_ADMIN_CARS_PAGE_COMMAND, new GoToAdminCarsPageCommand());
		commands.put(ADMIN_ADD_CAR_PAGE, new AdminAddCarCommand());
		commands.put(TO_ADMIN_ADD_CAR_PAGE_COMMAND, new GoToAdminAddCarPageCommand());
		commands.put(FIND_ORDER_BY_ID_COMMAND, new FindOrderByIdCommand());
	}
	
	private void initializeUserCommands() {
		commands.put(TO_ORDERS_PAGE_COMMAND, new GoToOrdersPageCommand());
	}

	private void initializeSignUpCommands() {
		commands.put(SIGN_UP_PAGE, new SignUpCommand());
		commands.put(TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
		commands.put(CODE_ENTRY_PAGE, new CodeEntryCommand());
		commands.put(TO_CODE_ENTRY_PAGE_COMMAND, new GoToCodeEntryPageCommand());
	}

	private void initializeCommonChangeCommands() {
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

	private void initializeAdminChangeCommands() {
		commands.put(CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
		commands.put(CHANGE_USER_ROLE_COMMAND, new ChangeUserRoleCommand());
		commands.put(CHANGE_USER_DISCOUNT_COMMAND, new ChangeUserDiscountCommand());
		commands.put(CHANGE_ORDER_STATUS_COMMAND, new ChangeOrderStatus());
		commands.put(CHANGE_CAR_DISCOUNT_COMMAND, new ChangeCarDiscountCommand());
		commands.put(CHANGE_CAR_COST_COMMAND, new ChangeCarCostCommand());
		commands.put(CHANGE_CAR_STATUS_COMMAND, new ChangeCarStatusCommand());
	}
}
