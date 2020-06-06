package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.CompanyRegistrationRepo;
import com.revature.jobportal.db.JobPositionRepo;
import com.revature.jobportal.model.Company;
import com.revature.jobportal.model.JobPosition;
import com.revature.jobportal.validation.Validation;

public class CompanyService {

	Scanner input = new Scanner(System.in);
	Validation validation = new Validation();
	CompanyRegistrationRepo companyRegistrationRepo = new CompanyRegistrationRepo();
	JobPositionRepo jobPositionRepo = new JobPositionRepo();

	public void signUp() {

		System.out.println("**Create new company account**");
		Company company = new Company();

		System.out.print("Enter company name: ");
		String companyName = input.nextLine();
		company.setCompanyName(companyName);

		System.out.print("Enter location: ");
		String companyLocation = input.nextLine();
		company.setCompanyLocation(companyLocation);

		System.out.print("Enter email: ");
		String companyEmail = input.nextLine();
		company.setCompanyEmail(companyEmail);

		System.out.print("Enter password: ");
		String companyPassword = input.nextLine();
		company.setPassword(companyPassword);

		boolean exist = validation.companyRegistrationValidation(company);
		if (!exist) {
			companyRegistrationRepo.addCompany(company);
			System.out.println("Successfully registered!");
		} else {
			System.out.println("Company already exist!");
		}
	}

	public void signIn() {

		System.out.println("***Company SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Company company = validation.credentialCheck(email, password);

		if (company != null) {
			System.out.println("\nWelcome back: " + company.getCompanyName().toUpperCase());
			System.out.println("\n             1. Post A Job\n        " + "     2. View Submitted Application\n");
			System.out.println("\n Posted jobs: ");
			for (JobPosition j : jobPositionRepo.getJobPosition()) {
				if (j.getCompany().equals(company.getCompanyName())) {
					System.out.println("\n " + j.getTitle() + " | " + j.getDescription() + " | " + j.getLocation());
				}
			}
			String userInput = input.nextLine();

			if (userInput.equals("1")) {

				System.out.print("Enter job title: ");
				String title = input.nextLine();
				System.out.print("Enter location: ");
				String location = input.nextLine();
				System.out.print("Enter description: ");
				String description = input.nextLine();

				JobPosition job = new JobPosition(title, location, description, company.getCompanyName());
				jobPositionRepo.addJobPosition(job);

				System.out.println("Job successfully posted!");
			} else if (userInput.equals("2")) {

			}

		} else {
			System.out.println("username/password not valid");
		}
	}
}
