package com.revature.jobportal.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.jobportal.model.Applicant;

public class ApplicantResgistrationRepo {

	private static List<Applicant> listOfApplicants = new ArrayList<Applicant>(
			Arrays.asList(new Applicant("Joe", "Doe", "joedoe@gmail.com", "pass"),
					new Applicant("John", "Davis", "johndavis@gmail.com", "pass"),
					new Applicant("Sam", "Adam", "samadam@gmail.com", "pass")));

	public List<Applicant> getApplicants() {
		return listOfApplicants;
	}

	public void addApplicant(Applicant applicant) {
		listOfApplicants.add(applicant);
	}
}
