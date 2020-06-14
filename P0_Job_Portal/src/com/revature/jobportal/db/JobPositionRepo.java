package com.revature.jobportal.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.jobportal.model.JobPosition;
import com.revature.jobportal.service.ConnectionService;

public class JobPositionRepo {
	
	//calls ConnectionService class to establish database connection
	ConnectionService connectionService = new ConnectionService();
	/*
	private static List<JobPosition> listOfJobPosition = new ArrayList<JobPosition>(Arrays.asList(
			new JobPosition("Java Developer", "North Carolina", "3 years of backend experience", "Bank of America"),
			new JobPosition("Automation Tester", "Washington", "6 years of automation testing experience", "Google"),
			new JobPosition("Share Point Developer", "Washington", "4 years of share point experience", "Google"),
			new JobPosition("UI Developer", "Washington", "9 years of UI developer experience", "Google"),
			new JobPosition("Application Support", "Virginia", "1 year of IT experience", "Fannie Mae")));
	*/
	
	public List<JobPosition> getJobPosition() {
		
		//lists all available job positions by checking database
		List<JobPosition> jpL = new ArrayList<JobPosition>();
		try {
			
			String q = "select * from jobposition";
			PreparedStatement ps = connectionService.getConnection().prepareStatement(q);
			
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			JobPosition jp = new JobPosition();
			jp.setId(rs.getInt("jobpositionid"));
			jp.setCompany(rs.getString("company"));
			jp.setDescription(rs.getString("jobdescription"));
			jp.setLocation(rs.getString("joblocation"));
			jp.setTitle(rs.getString("jobtitle"));				
			jpL.add(jp);
			}
	} catch (SQLException e) {
		System.out.println("Exception: " + e.getMessage());
		e.printStackTrace();
	}		
	return jpL;
	}
	//adds job positions to database
	public void addJobPosition(JobPosition jobPosition) {
		int i = 10;
		try {
		PreparedStatement jobStatement = connectionService.getConnection().
				prepareStatement("INSERT INTO company VALUES (?, ?, ?, ?, ?)");
		jobStatement.setInt(1, i);
		jobStatement.setString(2, jobPosition.getCompany());
		jobStatement.setString(3, jobPosition.getDescription());
		jobStatement.setString(4, jobPosition.getLocation());
		jobStatement.setString(5, jobPosition.getTitle());
		jobStatement.executeUpdate();
		i++;
		
		} catch(SQLException e) {
		}
	}
}
