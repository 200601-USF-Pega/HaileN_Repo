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
	
	//takes in company input
	Scanner input = new Scanner(System.in);
	Validation validation = new Validation();
	CompanyRegistrationRepo companyRepo = new CompanyRegistrationRepo();
	JobPositionRepo jobPositionRepo = new JobPositionRepo();

	//takes company information and creates account if company doesn't exist in database
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

		Company c = companyRepo.checkCompany(companyEmail);
		if(c != null) {
			System.out.println("Company already exist!");
		} else {
			companyRepo.addCompany(company);
			System.out.println("Successfully registered!");
		}
	}

	public void signIn() throws Exception {
		
		//takes in company input to check credential and signIn. 
		System.out.println("***Company SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Company company = companyRepo.getCompany(email, password);
		boolean check;

		do {
			check = false;
			if (company != null) {
				
				//welcomes back company and gives option to post a job, view candidates, or signOut
				System.out.println("\nWelcome back: " + company.getCompanyName().toUpperCase());
				System.out.println("\n             1. Post new Job\n        " + "     2. View Candidates Application\n"
						+ "             3. Sign out");
				
				//shows previously posted jobs
				System.out.println("\n ***Posted jobs*** ");
				for (JobPosition j : jobPositionRepo.getJobPosition()) {
					if (j.getCompany().equals(company.getCompanyName())) {
						System.out.println("\n " + j.getTitle() + " | " + j.getDescription() + " | " + j.getLocation());
					}
				}
				String userInput = input.nextLine();
				
				//takes new job information and posts job.
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
					
					//shows submitted applications if any
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
							
					//takes back to previous menu
							System.out.print("       press 'b' to go back ");
							b = input.nextLine();
							if (b.equalsIgnoreCase("b")) {
								check = true;
							}
						} while (!b.equalsIgnoreCase("b"));
					} else {
						String b = null;
						do {
							
					//gives option to review candidate's profile or go back to previous menu.
							System.out.print("\n                       1. Review candidate profiles\n        "
									+ "               2. go back ");
							b = input.nextLine();
							
							//go back to previous menu
							if (b.equals("2")) {
								check = true;
							//starts candidate review
							} else if (b.equals("1")) {
								System.out.println("\n==== Review Candidate Profiles ====\n");
								for (JobPosition j : jobPositionRepo.getJobPosition()) {
									if (j.getCompany().equals(company.getCompanyName())) {
										for (Applicant a : j.getApplicants()) {
											String ad = null;
											do {
												System.out.println("         " + a.getFirstName() + " "
														+ a.getLastName() + " " + a.getEmail());
												
												//gives option to accept/decline application.
												System.out.print("\n                       1. Accept\n        "
														+ "               2. Decline ");
												ad = input.nextLine();
												
												//accepts applicant
												if (ad.equals("1")) {
													a.setStatus("accepted");
													System.out.println(a.getFirstName() + " " + a.getLastName() + " " + " ***profile has been successfully accepted.");
													Thread.sleep(1500);
													
												//declines applicant
												} else if (ad.equals("2")) {
													a.setStatus("declined");
													System.out.println(a.getFirstName() + " " + a.getLastName() + " " + " ***profile has been successfully declined.");
													Thread.sleep(1500);
												}
											} while (!ad.equals("1") && !ad.equals("2"));
										}
										System.out.println();
									}
								}
								check = true;
							}
						} while (!b.equals("2") && !b.equals("1"));
					}
					
					//signOut company
				} else if (userInput.equals("3")) {
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
