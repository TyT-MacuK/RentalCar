package by.training.carrent.model.connection;

import java.util.TimerTask;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionTimerTask extends TimerTask {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void run() {
		logger.log(Level.INFO, "connection timer task is checking connection pool");
		ConnectionPool.getInstance().addLeakedConnections();
	}
}
