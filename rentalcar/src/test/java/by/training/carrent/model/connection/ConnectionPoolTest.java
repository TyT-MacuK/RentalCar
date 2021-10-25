package by.training.carrent.model.connection;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ConnectionPoolTest {
	 private ConnectionPool connectionPool;
	 
	@BeforeClass
	public void beforeClass() {
		connectionPool = ConnectionPool.getInstance();
	}

	@Test(description = "Testing method getConnection")
	public void getConnectionTest() {
		Connection connection = connectionPool.getConnection();
		Assert.assertNotNull(connection);
	}
	
	@Test(description = "Testing method releaseConnection")
	public void releaseConnectionTest() {
		Connection connection = connectionPool.getConnection();
		Assert.assertTrue(connectionPool.releaseConnection(connection));
	}
	
	@Test(description = "Testing method destroyPool")
	public void destroyPoolTest() {
		Assert.assertTrue(connectionPool.destroyPool());
	}

	@AfterClass
	public void afterClass() {
		connectionPool.destroyPool();
	}
}
