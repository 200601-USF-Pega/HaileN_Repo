package com.revature.jobportal.service;

import java.util.Scanner;

import com.revature.jobportal.db.ApplicantResgistrationRepo;
import com.revature.jobportal.db.JobPositionRepo;
import com.revature.jobportal.mainmenu.MainMenu;
import com.revature.jobportal.model.Applicant;
import com.revature.jobportal.model.JobPosition;

public class ApplicantService {
	
	//takes in applicant input.
	Scanner input = new Scanner(System.in);
	JobPositionRepo jobPositionRepo = new JobPositionRepo();
	ApplicantResgistrationRepo applicantRepo = new ApplicantResgistrationRepo();
	
	//takes applicant information and creates account if applicant doesn't exist in database.
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

		Applicant a = applicantRepo.checkApplicant(email);
		if(a != null) {
			System.out.println("Applicant already exist!");
		} else {
			applicantRepo.addApplicant(applicant);
			System.out.println("Successfully registered!");
		}
	}

	public void signIn() throws Exception {
		
		//takes in applicant input to check credential and signIn.
		System.out.println("***Applicant SignIn***\n");
		System.out.print("Enter email: ");
		String email = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();

		Applicant applicant = applicantRepo.getApplicant(email, password);
		boolean check;

		do {
			check = false;
			if (applicant != null) {
				
				//welcomes back applicant and displays submitted jobs.
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
				//gives option to apply for a new job, view application status, or signOut.
				System.out.println("\n        1. Apply for a new job \n        2. View application status"
						+ "\n        3. Sign out");
				String userInput = input.nextLine();
				
				//start the apply for a new job process
				if (userInput.equals("1")) {
					
					//asks which job to apply for
					System.out.print("\nEnter job id: ");
					String jobId = input.nextLine();
					
					//calls JobPositionrepo class to pull job
					for (JobPosition j : jobPositionRepo.getJobPosition()) {
						if (Integer.parseInt(jobId) == j.getId()) {
							System.out.println("\n" + j.getCompany() + "\n   " + j.getTitle() + "\n   "
									+ j.getDescription() + "\n   " + j.getLocation());
							
		            //asks if applicant want to submit application
							System.out.print("\nDo you want to submit your profile for this position? (y/n) ");
							String submit = input.nextLine();
							if (submit.equalsIgnoreCase("y")) {
								applicant.setStatus("reviewing");
								j.addApplicant(applicant);
								
			       //confirms application submission and goes back to apply for a new job, view application status, or signOut
								System.out.println("***You have successfully submitted your profile.***");
								check = true;
								Thread.sleep(3000);
							} else if (submit.equalsIgnoreCase("n")) {
								check = true;
							}

						}
					}
					//shows submitted applications.
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
						
					// shows message if no applications where submitted. and gives option to go back to previous menu
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
					
					// signsOut applicant
				} else if (userInput.equals("3")) {
					System.out.println("Successfully signed out!\n");
					Thread.sleep(3000);
					MainMenu menu = new MainMenu();
					menu.start();
				}
			} else {
				System.out.println("Username/Password Incorrect! ....press any key to continue.");
			}
		} while (check);
	}
}
