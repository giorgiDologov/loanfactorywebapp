package ernest.giorgi.service;

import java.time.LocalDate;

import ernest.giorgi.domain.Loan;

public interface LoanFactory {
	
	public Loan createLoan(int durationMonths, double nominalInterestRate, double totalLoanAmount, LocalDate startDate);


}
