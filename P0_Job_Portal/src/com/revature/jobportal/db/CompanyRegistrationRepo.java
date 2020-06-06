package com.revature.jobportal.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.jobportal.model.Company;

public class CompanyRegistrationRepo {
private static List<Company> listOfCompany = new ArrayList<Company>(Arrays.asList(
		new Company("Bank of America","North Carolina", "bankofamerica@bankofamerica.com","pass"),	
		new Company("Fannie Mae","Virginia", "fanniemae@fanniemae.com","pass"),
		new Company("Google","Washington", "google@google.com","pass")
		));
	
	public List<Company> getCompany(){
		return listOfCompany;
	}
	
	public void addCompany(Company company) {
		listOfCompany.add(company);
	}
}
