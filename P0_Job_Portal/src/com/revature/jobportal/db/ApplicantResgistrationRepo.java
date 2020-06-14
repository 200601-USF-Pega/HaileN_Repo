package com.revature.jobportal.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.jobportal.service.ConnectionService;
import com.revature.jobportal.model.Applicant;


public class ApplicantResgistrationRepo {
	
	//calls ConnectionService class to establish database connection
	ConnectionService connectionService = new ConnectionService();

	public Applicant getApplicant(String em, String p) {
		
		try {
			//pulls applicant from database using email and password
			String q = "select * from applicant where email= ?  and applicantpassword = ? limit 1";
				PreparedStatement ps = connectionService.getConnection().prepareStatement(q);
				ps.setString(1, em);
				ps.setString(2, p);
				
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				
				Applicant a = new Applicant();
				a.setFirstName(rs.getString("firstname"));
				a.setLastName(rs.getString("lastname"));
				a.setEmail(rs.getString("email"));				
				return a;
				}
			
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}		
		return null;
	}
	
	//adds applicant to database
	public void addApplicant(Applicant applicant) {
		int i = 10;
		try {
		PreparedStatement applicantStatement = connectionService.getConnection().
				prepareStatement("INSERT INTO applicant VALUES (?, ?, ?, ?, ?)");
		applicantStatement.setInt(1, i);
		applicantStatement.setString(2, applicant.getFirstName());
		applicantStatement.setString(3, applicant.getLastName());
		applicantStatement.setString(4, applicant.getEmail());
		applicantStatement.setString(5, applicant.getPasword());
		applicantStatement.executeUpdate();
		i++;
		
		} catch(SQLException e) {
			
			
		}
	}
	//checks if applicant already exist in database
	public Applicant checkApplicant(String em) {
		try {
			String q = "select * from applicant where email= ? limit 1";
			PreparedStatement ps = connectionService.getConnection().prepareStatement(q);
			ps.setString(1, em);
			
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			Applicant a = new Applicant();
			a.setFirstName(rs.getString("firstname"));
			a.setLastName(rs.getString("lastname"));
			a.setEmail(rs.getString("email"));				
			return a;
			}
		
	} catch (SQLException e) {
		System.out.println("Exception: " + e.getMessage());
		e.printStackTrace();
	}		
	return null;
	}
}
