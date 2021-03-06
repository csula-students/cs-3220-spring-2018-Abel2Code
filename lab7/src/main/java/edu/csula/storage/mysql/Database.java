package edu.csula.storage.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import edu.csula.models.Credentials;

public class Database implements edu.csula.storage.Database{
	// FIXME: don't hard commit your credential
	private static final String database = "cs3220stu54";
	// private static final String host = "localhost";
	private static final String host = "cs3.calstatela.edu";
	private static final String url = String.format("jdbc:mysql://%s/%s", host, database);
	static final String username = Credentials.username;
	private static final String password = Credentials.password;

	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch( ClassNotFoundException e ) {
			throw new IllegalStateException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
