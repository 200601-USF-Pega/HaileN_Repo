package com.revature.jobportal.validation;

import com.revature.jobportal.db.ApplicantResgistrationRepo;
import com.revature.jobportal.db.CompanyRegistrationRepo;
import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.model.Company;

public class Validation {

	ApplicantResgistrationRepo applicantResgistrationRepo = new ApplicantResgistrationRepo();
	CompanyRegistrationRepo companyRegistrationRepo = new CompanyRegistrationRepo();

	public boolean applicantRegistrationValidation(Applicant applicant) {

		for (Applicant u : applicantResgistrationRepo.getApplicants()) {
			if (u.getEmail().equals(applicant.getEmail())) {
				return true;
			}
		}
		return false;
	}

	public boolean companyRegistrationValidation(Company company) {

		for (Company c : companyRegistrationRepo.getCompany()) {
			if (c.getCompanyEmail().equals(company.getCompanyEmail())) {
				return true;
			}
		}
		return false;
	}

	public Company credentialCheckCompany(String email, String password) {

		Company company = null;
		for (Company c : companyRegistrationRepo.getCompany()) {
			if (c.getCompanyEmail().equals(email) && c.getPassword().equals(password)) {
				company = c;
			}
		}
		return company;
	}

	public Applicant credentialCheckApplicant(String email, String password) {

		Applicant applicant = null;
		for (Applicant a : applicantResgistrationRepo.getApplicants()) {
			if (a.getEmail().equals(email) && a.getPasword().equals(password)) {
				applicant = a;
			}
		}
		return applicant;
	}

}