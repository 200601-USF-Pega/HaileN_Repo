package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.ApplicantResgistrationRepo;
import com.revature.jobportal.db.JobPositionRepo;
import com.revature.jobportal.mainmenu.MainMenu;
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

	public void signIn() throws Exception {

		System.out.println("***Applicant SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Applicant applicant = validation.credentialCheckApplicant(email, password);
		boolean check;

		do {
			check = false;
			if (applicant != null) {
				System.out.println("\nWelcome back: " + applicant.getFirstName().toUpperCase() + " "
						+ applicant.getLastName().toUpperCase() + " \n");
				for (JobPosition j : jobPositionRepo.getJobPosition()) {
					System.out.print(j.getId() + " " + j.getCompany());

					for (Applicant a : j.getApplicants()) {
						if (a.getEmail().equals(applicant.getEmail())) {
							System.out.print("   ***submitted");
						}
					}
					System.out.println(
							"\n     " + j.getTitle() + "\n     " + j.getDescription() + "\n     " + j.getLocation());
					System.out.println();
				}
				System.out.println("\n        1. Apply for a new job \n        2. View application status"
						+ "\n        3. Sign out");
				String userInput = input.nextLine();

				if (userInput.equals("1")) {
					System.out.print("\nEnter job id: ");
					String jobId = input.nextLine();
					for (JobPosition j : jobPositionRepo.getJobPosition()) {
						if (Integer.parseInt(jobId) == j.getId()) {
							System.out.println("\n" + j.getCompany() + "\n   " + j.getTitle() + "\n   "
									+ j.getDescription() + "\n   " + j.getLocation());
							System.out.print("\nDo you want to submit your profile for this position? (y/n) ");
							String submit = input.nextLine();
							if (submit.equalsIgnoreCase("y")) {
								applicant.setStatus("reviewing");
								j.addApplicant(applicant);
								System.out.println("***You have successfully submitted your profile.***");
								check = true;
								Thread.sleep(3000);
							} else if (submit.equalsIgnoreCase("n")) {
								check = true;
							}

						}
					}
				} else if (userInput.equals("2")) {
					boolean no = true;
					System.out.println("====== APPLIED JOBS ======\n");
					for (JobPosition j : jobPositionRepo.getJobPosition()) {
						for (Applicant a : j.getApplicants()) {
							if (a.getEmail().equals(applicant.getEmail())) {
								no = false;
								System.out.println(j.getCompany() + "\n     " + j.getTitle() + "\n     "
										+ j.getDescription() + "\n     " + j.getLocation());
								System.out.println("                                status: " + applicant.getStatus());
								System.out.println();
							}

						}
					}
					if(no) {
						System.out.println("**no application submitted**\n");
					}
					String b = null;
					do {
						System.out.print("press 'b' to go back: ");
						b = input.nextLine();
						if (b.equalsIgnoreCase("b")) {
							check = true;
						}
					} while (!b.equalsIgnoreCase("b"));
				} else if (userInput.equals("3")) {
					System.out.println("Successfully signed out!\n");
					Thread.sleep(3000);
					MainMenu menu = new MainMenu();
					menu.start();
				}
			} else {
				System.out.println("username/password incorrect!");
			}
		} while (check);
	}
}
