package com.revature.jobportal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.jobportal.dao.JobPositionRepo;
import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.model.JobPosition;
import com.revature.jobportal.service.ConnectionService;

public class JobPositionRepoImpl implements JobPositionRepo {
	
	//calls ConnectionService class to establish database connection
	Connection connection = ConnectionService.getConnection();

	
	public List<JobPosition> getJobPosition() {
		
		//lists all available job positions by checking database
		List<JobPosition> jpL = new ArrayList<JobPosition>();
		try {
			
			String q = "select * from jobposition";
			PreparedStatement ps = connection.prepareStatement(q);
			
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			JobPosition jp = new JobPosition();
			jp.setId(rs.getInt("jobpositionid"));
			jp.setCompany(rs.getString("company"));
			jp.setDescription(rs.getString("jobdescription"));
			jp.setLocation(rs.getString("joblocation"));
			jp.setTitle(rs.getString("jobtitle"));	
			jp.setApplicant(rs.getString("applicant"));
			jp.setStatus(rs.getString("status"));
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
		try {
		PreparedStatement jobStatement = connection.
				prepareStatement("INSERT INTO jobposition VALUES (?, ?, ?, ?, ?, ?)");
		int id = 1 + (int)(Math.random()*9999);
		jobStatement.setInt(1, id);
		jobStatement.setString(5, jobPosition.getCompany());
		jobStatement.setString(3, jobPosition.getDescription());
		jobStatement.setString(4, jobPosition.getLocation());
		jobStatement.setString(2, jobPosition.getTitle());
		jobStatement.setString(6, jobPosition.getCompany());
		jobStatement.execute();
		id++;
		} catch(SQLException e) {
		}
	}
	public void applyPosition(JobPosition jobPosition, Applicant a) {
		try {
			PreparedStatement jobStatement = connection.
					prepareStatement("INSERT INTO jobposition VALUES (?, ?, ?, ?, ?, ?, ?)");
			int id = 1 + (int)(Math.random()*9999);
			jobStatement.setInt(1, id);
			jobStatement.setString(5, jobPosition.getCompany());
			jobStatement.setString(3, jobPosition.getDescription());
			jobStatement.setString(4, jobPosition.getLocation());
			jobStatement.setString(2, jobPosition.getTitle());
			jobStatement.setString(6, a.getId());
			jobStatement.setString(7, jobPosition.getStatus());
			jobStatement.execute();
			id++;
			} catch(SQLException e) {
			}
	}
	public List<JobPosition> getAppliedPosition(Applicant a) {
		List<JobPosition> jpL = new ArrayList<JobPosition>();
		try {
			
			String q = "select * from jobposition where applicant = ? ";
			PreparedStatement ps = connection.prepareStatement(q);
			ps.setString(1, a.getId());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {				
			JobPosition jp = new JobPosition();
			jp.setCompany(rs.getString("company"));
			jp.setDescription(rs.getString("jobdescription"));
			jp.setLocation(rs.getString("joblocation"));
			jp.setTitle(rs.getString("jobtitle"));	
			jp.setApplicant(rs.getString("applicant"));
			jp.setStatus(rs.getString("status"));
			jpL.add(jp);
			}
	} catch (SQLException e) {
		System.out.println("Exception: " + e.getMessage());
		e.printStackTrace();
	}	
		return jpL;
	}
	public void updatePosition(JobPosition jobPosition) {
		try {
			PreparedStatement ps = connection.
					prepareStatement("update jobposition set status = ? where jobpositionid = ?");

			ps.setString(1, jobPosition.getStatus());
			ps.setInt(2, jobPosition.getId());
			ps.execute();
			} catch(SQLException e) {
			}
	}
}
