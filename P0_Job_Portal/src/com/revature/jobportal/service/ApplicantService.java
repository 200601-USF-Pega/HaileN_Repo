package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.ApplicantResgistrationRepo;
import com.revature.jobportal.db.JobPositionRepo;
import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.model.JobPosition;
import com.revature.jobportal.validation.Validation;

public class ApplicantService {

	Scanner input = new Scanner(System.in);
	ApplicantResgistrationRepo applicantResgistrationRepo = new ApplicantResgistrationRepo();
	JobPositionRepo jobPositionRepo = new JobPositionRepo();
	Validation validation = new Validation();

	public void signUp() {
		System.out.println("**Create new account**");

		System.out.print("Enter first name: ");
		String firstName = input.nextLine();

		System.out.print("Enter last name: ");
		String lastName = input.nextLine();

		System.out.print("Enter email: ");
		String email = input.nextLine();
		
		System.out.print("Enter password: ");
		String password = input.nextLine();
		
		Applicant applicant = new Applicant(firstName, lastName, email, password);
		boolean exist = validation.applicantRegistrationValidation(applicant);
		if (exist == false) {
			applicantResgistrationRepo.addApplicant(applicant);
			System.out.println("Successfully registered!");
		} else {
			System.out.println("Applicant already exist!");
		}

	}

	public void signIn() {

		System.out.println("***Applicant SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Applicant applicant = validation.credentialCheckApplicant(email, password);

		if (applicant != null) {
			System.out.println("\nWelcome back: " + applicant.getFirstName().toUpperCase() + " "
					+ applicant.getLastName().toUpperCase() + " \n");
			for (JobPosition j : jobPositionRepo.getJobPosition()) {
				System.out.println(j.getId() + " " + j.getCompany() + "\n     " + j.getTitle() + "\n     " + j.getDescription()
						+ "\n     " + j.getLocation());
				System.out.println();
			}
			System.out.println("\n        1. Apply for a new job \n        2. View application status");
			String userInput = input.nextLine();

			if (userInput.equals("1")) {
				System.out.print("\nEnter job id: ");
					String jobId = input.nextLine();
					for(JobPosition j: jobPositionRepo.getJobPosition()) {
						if(Integer.parseInt(jobId) == j.getId()) {
							System.out.println("\n" + j.getCompany() + "\n   " + j.getTitle() + "\n   " + j.getDescription()
									+ "\n   " + j.getLocation());
							System.out.print("\nDo you want to submit your profile for this position? (y/n) ");
							String submit = input.nextLine();
							if(submit.equalsIgnoreCase("y")) {
								j.addApplicant(applicant);
								System.out.println("submit");
							} else if(submit.equalsIgnoreCase("n")) {
								System.out.println("don't submit");
							}
						}
					}
			} else if (userInput.equals("2")) {

			}
		} else {
			System.out.println("username/password incorrect!");
		}
	}
}
