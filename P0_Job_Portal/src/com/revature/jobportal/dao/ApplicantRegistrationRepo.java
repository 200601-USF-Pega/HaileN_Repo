package com.revature.jobportal.dao;

import com.revature.jobportal.model.Applicant;

public interface ApplicantRegistrationRepo {

	public Applicant getApplicant(String em, String p);
	public void addApplicant(Applicant applicant);
	public Applicant checkApplicant(String em);
	public Applicant getAppliedApplicant(int id);
}
