package by.training.carrent.model.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();

	/** The instance. */
	private static ConnectionPool instance;

	/** The Constant DATABASE_PROPERTY_PATH. */
	private static final String DATABASE_PROPERTY_PATH = "properties/database.properties";

	/** The Constant DATABASE_URL. */
	private static final String DATABASE_URL = "url";
	
	/** The database url. */
	private static String urlDb;

	/** The Constant DATABASE_DRIVER. */
	private static final String DATABASE_DRIVER = "driverClassName";


	/** The Constant properties. */
	private static final Properties properties = new Properties();

	/** The Constant CONNECTION_POOL_SIZE. */
	private static final int CONNECTION_POOL_SIZE = 4;

	/** The Constant START_TIME. */
	private static final long START_TIME = 120000;

	/** The Constant STARTUP_TIME. */
	private static final long STARTUP_TIME = 300000;

	/** The Constant isInitialized. */
	private static final AtomicBoolean isInitialized = new AtomicBoolean();

	/** The free connections. */
	private final BlockingQueue<ProxyConnection> freeConnections;

	/** The given away connections. */
	private final BlockingQueue<ProxyConnection> givenAwayConnections;

	/** The connection timer. */
	private final Timer connectionTimer;

	/** The Constant locker. */
	private static final Lock locker = new ReentrantLock();

	static {
		try {
			InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTY_PATH);
			if (inputStream == null) {
				logger.log(Level.FATAL, "properties for connection to data base is not found : {}",
						DATABASE_PROPERTY_PATH);
				throw new RuntimeException("Fatal error. Properties file is not found");
			}
			properties.load(inputStream);
			urlDb = properties.getProperty(DATABASE_URL);
			if (urlDb == null) {
				logger.log(Level.FATAL, "url database does not found");
				throw new RuntimeException("Fatal error. Url database does not found");
			}
			String driver = properties.getProperty(DATABASE_DRIVER);
			if (driver == null) {
				logger.log(Level.FATAL, "driver class name does not found");
				throw new RuntimeException("Fatal error. Driver class name does not found");
			}
			Class.forName(driver);
		} catch (IOException | ClassNotFoundException e) {
			logger.log(Level.FATAL, "file cannot be read", e);
		}
	}

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {
		freeConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
		givenAwayConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_SIZE);
		connectionTimer = new Timer();
		connectionTimer.schedule(new ConnectionTimerTask(), START_TIME, STARTUP_TIME);
		initializeConnectionPool();
	}

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		if (!isInitialized.get()) {
			locker.lock();
			if (instance == null) {
				instance = new ConnectionPool();
				isInitialized.set(true);
				logger.log(Level.INFO, "Connection pool was created");
			}
			locker.unlock();
		}
		return instance;
	}

	/**
	 * Get the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		ProxyConnection proxyConnection = null;
		try {
			proxyConnection = freeConnections.take();
			givenAwayConnections.put(proxyConnection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "exception in method getConnection()", e);
			Thread.currentThread().interrupt();
		}
		return proxyConnection;
	}

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 * @return true, if connection was released
	 */
	public boolean releaseConnection(Connection connection) {
		boolean result = false;
		if (connection instanceof ProxyConnection && givenAwayConnections.remove(connection)) {
			try {
				freeConnections.put((ProxyConnection) connection);
				result = true;
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "exception in method getConnection()", e);
				Thread.currentThread().interrupt();
			}
		} else {
			logger.log(Level.ERROR, "error in method releaseConnection()");
		}
		return result;
	}

	/**
	 * Destroy pool.
	 *
	 * @return true, if connection pool was destroyed
	 */
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
		connectionTimer.cancel();
		deregisterDrivers();
		if (freeConnections.isEmpty()) {
			result = true;
		}
		return result;
	}

	/**
	 * Add the leaked connections.
	 */
	void addLeakedConnections() {
		int actualQuantityConnections = freeConnections.size() + givenAwayConnections.size();
		for (int i = actualQuantityConnections; i < CONNECTION_POOL_SIZE; i++) {
			try {
				Connection connection = DriverManager.getConnection(urlDb, properties);
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.offer(proxyConnection);
			} catch (SQLException e) {
				logger.log(Level.FATAL, "connection does not create", e);
				throw new RuntimeException("Fatal error. Connection does not create", e);
			}
		}
	}

	/**
	 * Deregister drivers.
	 */
	private void deregisterDrivers() {
		try {
			while (DriverManager.getDrivers().hasMoreElements()) {
				DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "exception when deregister driver", e);
		}
	}

	/**
	 * Initialize connection pool.
	 */
	private void initializeConnectionPool() {
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			try {
				Connection connection = DriverManager.getConnection(urlDb, properties);
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.offer(proxyConnection);
			} catch (SQLException e) {
				logger.log(Level.FATAL, "connection does not create", e);
				throw new RuntimeException("Fatal error. Connection does not create", e);
			}
		}
	}
}
