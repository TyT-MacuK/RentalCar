package by.training.carrent.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//TODO ConnectionPool
//TODO getConnection
//TODO releaseConnection
public class ConnectionPool {
	private static final Logger logger = LogManager.getLogger();
	private static ConnectionPool instance;
	private static final int CONNECTION_POOL_SIZE = 4;
	private static final AtomicBoolean isConnectionPoolCreated = new AtomicBoolean();
	private BlockingQueue<ProxyConnection> freeConnections;
	private BlockingQueue<ProxyConnection> givenAwayConnections;
	private final ReentrantLock locker = new ReentrantLock();

	private ConnectionPool() {
		freeConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
		givenAwayConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
		initializeConnectionPool();
	}

	public static ConnectionPool getInstance() {//TODO
		while (instance == null) {
			if (isConnectionPoolCreated.compareAndExchange(false, true)) {
				instance = new ConnectionPool();
				logger.log(Level.INFO, "Connection pool was created");
			}
		}
		return instance;
	}

	public Connection getConnection() {
		ProxyConnection proxyConnection = null;
		try {
			proxyConnection = freeConnections.take();
			givenAwayConnections.put(proxyConnection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "exception in method getConnection()", e);
			Thread.currentThread().interrupt();// TODO
		}
		return proxyConnection;
	}

	public boolean releaseConnection(Connection connection) {
		boolean result = false;
		if (connection instanceof ProxyConnection && givenAwayConnections.remove(connection)) {
			try {
				freeConnections.put((ProxyConnection) connection);
				result = true;
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "exception in method getConnection()", e);
				//TODO ServiceException???
			}
		} else {
			logger.log(Level.ERROR, "error in method releaseConnection()");
		}
		return result;
	}

	public boolean destroyPool() {
		boolean result = false;
		while (!freeConnections.isEmpty()) {
			try {
				freeConnections.take().reallyClose();
			} catch (SQLException e) {
				logger.log(Level.WARN, "connection is not close", e);
			} catch (InterruptedException e) {
				logger.log(Level.WARN, "current thread was interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		deregisterDrivers();
		if (freeConnections.isEmpty()) {
			result = true;
		}
		return result;
	}

	private void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Can not deregister driver", e);
			}
		});
	}

	private void initializeConnectionPool() {
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			try {
				Connection connection = ConnectionFactory.createConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.offer(proxyConnection);
			} catch (SQLException e) {
				logger.log(Level.FATAL, "connection does not create");
				throw new RuntimeException("FatalException. Connection does not create", e);
			}
		}
	}
}