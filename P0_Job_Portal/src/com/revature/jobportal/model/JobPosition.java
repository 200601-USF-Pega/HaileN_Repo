package com.revature.jobportal.model;

import java.util.ArrayList;
import java.util.List;

//job position details
public class JobPosition {

	private int id;
	private String title;
	private String location;
	private String description;
	private String company;
	private List<Applicant> applicants = new ArrayList<Applicant>();

	public JobPosition() {
		
	}
	public JobPosition(String title, String location, String description, String company) {

		this.title = title;
		this.location = location;
		this.description = description;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void addApplicant(Applicant applicant) {
		getApplicants().add(applicant);
	}

	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}

	public String getCompany() {
		return company;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "JobPositon [title=" + title + ", location=" + location + ", description=" + description + ", company="
				+ company + "]";
	}

}
