package com.revature.jobportal.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.jobportal.model.Company;
import com.revature.jobportal.service.ConnectionService;

public class CompanyRegistrationRepo {
	
	//calls ConnectionService class to establish database connection
	ConnectionService connectionService = new ConnectionService();
	
	public Company getCompany(String email, String pass){
		try {
			//pulls company from database using email and password
			String q = "select * from company where email= ?  and companypassword = ? limit 1";
			PreparedStatement ps = connectionService.getConnection().prepareStatement(q);
			ps.setString(1, email);
			ps.setString(2, pass);
			
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			Company c = new Company();
			c.setCompanyName(rs.getString("companyname"));
			c.setCompanyLocation(rs.getString("adress"));
			c.setCompanyEmail(rs.getString("email"));				
			return c;
			}
		
	} catch (SQLException e) {
		System.out.println("Exception: " + e.getMessage());
		e.printStackTrace();
	}		
	return null;
	}
	//adds company to database
	public void addCompany(Company company) {
		int i = 10;
		try {
		PreparedStatement companyStatement = connectionService.getConnection().
				prepareStatement("INSERT INTO company VALUES (?, ?, ?, ?, ?)");
		companyStatement.setInt(1, i);
		companyStatement.setString(2, company.getCompanyName());
		companyStatement.setString(3, company.getCompanyLocation());
		companyStatement.setString(4, company.getCompanyEmail());
		companyStatement.setString(5, company.getPassword());
		companyStatement.executeUpdate();
		i++;
		
		} catch(SQLException e) {
			
			
		}
	}
	
	//checks if company already exist in database
	public Company checkCompany(String companyEmail) {
	try {
			
			String q = "select * from company where email= ? limit 1";
			PreparedStatement ps = connectionService.getConnection().prepareStatement(q);
			ps.setString(1, companyEmail);
			
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			Company c = new Company();
			c.setCompanyName(rs.getString("companyname"));
			c.setCompanyLocation(rs.getString("adress"));
			c.setCompanyEmail(rs.getString("email"));				
			return c;
			}
		
	} catch (SQLException e) {
		System.out.println("Exception: " + e.getMessage());
		e.printStackTrace();
	}		
	return null;
	}
}
