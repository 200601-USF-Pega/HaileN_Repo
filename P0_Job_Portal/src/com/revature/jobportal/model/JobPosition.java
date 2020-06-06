package com.revature.jobportal.model;

public class JobPosition {

	private String title;
	private String location;
	private String description;
	private String company;

	public JobPosition(String title, String location, String description, String company) {

		this.title = title;
		this.location = location;
		this.description = description;
		this.company = company;
	}

	public String getCompany() {
		return company;
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
