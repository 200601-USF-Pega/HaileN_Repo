package com.revature.jobportal.mainmenu;

import java.util.Scanner;

import com.revature.jobportal.service.ApplicantService;
import com.revature.jobportal.service.CompanyService;

public class MainMenu {

	Scanner input = new Scanner(System.in);
	ApplicantService applicantService = new ApplicantService();
	CompanyService companyService = new CompanyService();

	public void start() {
		System.out.println("*** WELCOME TO JOB PORTAL ***\n \n1. Sign In\n2. Sign Up");

		do {
			String num = input.nextLine();
			if (num.equals("1")) {
				System.out.println("Sign in as a \n             1. Applicant\n        " + "     2. Company");
				String userInput = input.nextLine();

				if (userInput.equals("1")) {
					applicantService.signIn();
				} else if (userInput.equals("2")) {
					companyService.signIn();
				}

			} else if (num.equals("2")) {
				System.out.println("Sign up as a \n             1. Applicant\n        " + "     2. Company");
				String userInput = input.nextLine();

				if (userInput.equals("1")) {
					applicantService.signUp();
				} else if (userInput.equals("2")) {
					companyService.signUp();
				}
			}
		} while (true);
	}
}
