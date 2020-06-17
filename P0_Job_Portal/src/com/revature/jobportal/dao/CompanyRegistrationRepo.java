package com.revature.jobportal.dao;

import com.revature.jobportal.model.Company;

public interface CompanyRegistrationRepo {

	public Company getCompany(String email, String pass);
	public void addCompany(Company company);
	public Company checkCompany(String companyEmail);
}
