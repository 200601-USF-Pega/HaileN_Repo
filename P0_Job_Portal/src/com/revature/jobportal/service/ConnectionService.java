package com.revature.jobportal.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionService {

	private static Connection connection;
	static {
		try {
			FileInputStream fis = new FileInputStream("connection.prop");
			Properties p = new Properties();
			p.load(fis);

			// database connection driver
			connection = DriverManager.getConnection(p.getProperty("hostname"), p.getProperty("username"),
					p.getProperty("password"));

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static Connection getConnection() {
		return connection;
	}
}
