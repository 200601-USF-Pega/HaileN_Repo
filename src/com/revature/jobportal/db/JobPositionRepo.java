package com.revature.jobportal.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.jobportal.model.JobPosition;

public class JobPositionRepo {

	private static List<JobPosition> listOfJobPosition = new ArrayList<JobPosition>(Arrays.asList(
			new JobPosition("Java Developer", "North Carolina", "3 years of backend experience", "Bank of America"),
			new JobPosition("Automation Tester", "Washington", "6 years of automation testing experience", "Google"),
			new JobPosition("Share Point Developer", "Washington", "4 years of share point experience", "Google"),
			new JobPosition("UI Developer", "Washington", "9 years of UI developer experience", "Google"),
			new JobPosition("Application Support", "Virginia", "1 year of IT experience", "Fannie Mae")));

	public List<JobPosition> getJobPosition() {
		return listOfJobPosition;
	}

	public void addJobPosition(JobPosition jobPosition) {
		listOfJobPosition.add(jobPosition);
	}
}
