package by.training.carrent.model.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	private static final Logger logger = LogManager.getLogger();
	private static final String DATABASE_PROPERTY_PATH = "properties/database.properties";
	private static final String DATABASE_URL = "url";
	private static final String DATABASE_DRIVER = "driverClassName";
	private static String urlDb;
	private static final Properties properties = new Properties();

	static {
		try {
			InputStream inputStream = ConnectionFactory.class.getClassLoader()
					.getResourceAsStream(DATABASE_PROPERTY_PATH);
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
		} catch (IOException e) {
			logger.log(Level.FATAL, "file cannot be read");
			throw new RuntimeException("Fatal error. Properties file cannot be read", e);
		}
	}

	private ConnectionFactory() {
	}

	static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(urlDb, properties);
	}
}
