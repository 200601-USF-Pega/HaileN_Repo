package com.revature.jobportal.model;

public class Company {

	private String companyName;
	private String companyLocation;
	private String companyEmail;
	private String password;

	public Company() {

	}

	public Company(String companyName, String companyLocation, String companyEmail, String password) {
		super();
		this.companyName = companyName;
		this.companyLocation = companyLocation;
		this.companyEmail = companyEmail;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", companyLocation=" + companyLocation + ", companyEmail="
				+ companyEmail + ", password=" + password + "]";
	}

}
