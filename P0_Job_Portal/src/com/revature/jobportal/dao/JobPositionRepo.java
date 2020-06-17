package com.revature.jobportal.dao;

import java.util.List;

import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.model.JobPosition;

public interface JobPositionRepo {

	public List<JobPosition> getJobPosition();
	public void addJobPosition(JobPosition jobPosition);
	public void applyPosition(JobPosition jobPosition, Applicant a);
	public List<JobPosition> getAppliedPosition(Applicant a);
	public void updatePosition(JobPosition jobPosition);
	 
}
