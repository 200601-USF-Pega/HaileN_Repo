package com.revature.jobportal.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionService {
	
	//set Connection name
	private Connection connection;
	
	//set file path to file containing database connection details.
	public ConnectionService() {
		try  {
			FileInputStream fis = new FileInputStream("connection.prop");
			Properties p = new Properties();
			p.load(fis);
			
			//database connection driver
			connection = DriverManager.getConnection(p.getProperty("hostname"), 
					p.getProperty("username"), p.getProperty("password"));
			
		} catch (Exception e) {
		}
	}
	
	public Connection getConnection() {	
			return connection;
	}
	
	@Override
	public void finalize() {
		try {
			connection.close();
		} catch(Exception e) {
			
		}
	}

}
