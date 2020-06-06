package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.ApplicantResgistrationRepo;
import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.validation.Validation;

public class ApplicantService {

	Scanner input = new Scanner(System.in);
	ApplicantResgistrationRepo applicantResgistrationRepo = new ApplicantResgistrationRepo();
	Validation validation = new Validation();

	public void signUp() {
		System.out.println("**Create new account**");
		Applicant applicant = new Applicant();

		System.out.print("Enter first name: ");
		String firstName = input.nextLine();
		applicant.setFirstName(firstName);

		System.out.print("Enter last name: ");
		String lastName = input.nextLine();
		applicant.setLastName(lastName);

		System.out.print("Enter email: ");
		String email = input.nextLine();
		applicant.setEmail(email);

		System.out.print("Enter password: ");
		String password = input.nextLine();
		applicant.setPasword(password);

		boolean exist = validation.applicantRegistrationValidation(applicant);
		if (exist == false) {
			applicantResgistrationRepo.addApplicant(applicant);
			System.out.println("Successfully registered!");
		} else {
			System.out.println("Applicant already exist!");
		}

	}

	public void signIn() {

	}
}
