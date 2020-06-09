package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.CompanyRegistrationRepo;
import com.revature.jobportal.db.JobPositionRepo;
import com.revature.jobportal.mainmenu.MainMenu;
import com.revature.jobportal.model.Applicant;
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

	public void signIn() throws Exception {

		System.out.println("***Company SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Company company = validation.credentialCheckCompany(email, password);
		boolean check;

		do {
			check = false;
			if (company != null) {
				System.out.println("\nWelcome back: " + company.getCompanyName().toUpperCase());
				System.out.println("\n             1. Post new Job\n        " + "     2. View Candidates Application\n"
						+ "             3. Sign out");
				System.out.println("\n ***Posted jobs*** ");
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
					System.out.println("==== View Submitted Application ====\n");
					boolean no = true;
					for (JobPosition j : jobPositionRepo.getJobPosition()) {
						if (j.getCompany().equals(company.getCompanyName())) {
							if (!j.getApplicants().isEmpty()) {
								no = false;
								System.out.println("+Position+\n         " + j.getTitle() + " | " + j.getDescription()
										+ " | " + j.getLocation());
								System.out.println("+Candidates+");
								for (Applicant a : j.getApplicants()) {
									System.out.println("         " + a.getFirstName() + " " + a.getLastName() + " "
											+ a.getEmail());
								}
								System.out.println();
							}
						}
					}
					if (no) {
						System.out.println("**no application submitted**\n");
						String b = null;
						do {

							System.out.print("       press 'b' to go back ");
							b = input.nextLine();
							if (b.equalsIgnoreCase("b")) {
								check = true;
							}
						} while (!b.equalsIgnoreCase("b"));
					} else {
						String b = null;
						do {
							System.out.print(
									"\n                       1. Review candidate profiles\n        " + "               2. go back ");
							b = input.nextLine();
							if (b.equals("2")) {
								check = true;
							} else if (b.equals("1")) {

							}
						} while (!b.equals("2") && !b.equals("1"));
					}
				}else if (userInput.equals("3")) {
					System.out.println("Successfully signed out!\n");
					Thread.sleep(3000);
					MainMenu menu = new MainMenu();
					menu.start();
				}

			} else {
			}
		} while (check);
	}
}
