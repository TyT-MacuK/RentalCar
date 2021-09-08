package by.training.carrent.command;

public enum CommandType {
	SIGN_UP();// new SignUpCommand()

	private ActionCommand command;

	public ActionCommand getCommand() {
		return command;
	}

	public void setCommand(ActionCommand command) {
		this.command = command;
	}
}
