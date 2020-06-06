package com.revature.jobportal.model;

public class Applicant {

	private String firstName;
	private String lastName;
	private String email;
	private String pasword;

	public Applicant() {
		
	}

	public Applicant(String firstName, String lastName, String email, String pasword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pasword = pasword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pasword=" + pasword
				+ "]";
	}

}
