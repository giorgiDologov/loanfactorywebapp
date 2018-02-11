package ernest.giorgi.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ernest.giorgi.domain.Loan;
import ernest.giorgi.domain.Repayment;
import ernest.giorgi.repository.LoanRepository;
import ernest.giorgi.repository.RepaymentRepository;
import ernest.giorgi.service.LoanFactory;

@Service
public class LoanFactoryImpl implements LoanFactory{

    public double NUMBER_OF_MONTHS_IN_YEAR = 12;
    public double NUMBER_OF_DAYS_IN_MONTH = 30;
    public double NUMBER_OF_DAYS_IN_YEAR = 360;
    
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private RepaymentRepository repaymentRepository;

    @Override
    public Loan createLoan(int durationMonths, double nominalInterestRate, double totalLoanAmount, LocalDate startDate) {

        // Convert interest rate into a decimal

        nominalInterestRate /= 100.00;

        // Monthly interest rate

        double monthlyNominalRate = nominalInterestRate / NUMBER_OF_MONTHS_IN_YEAR;

        // Calculate the monthly annuity

        // The Math.pow() method is used calculate values raised to a power

        double annuity =
                (totalLoanAmount*monthlyNominalRate) /
                        (1-Math.pow(1+monthlyNominalRate, -durationMonths));

        //Create & return loan:
        
        Loan loan = new Loan(durationMonths, nominalInterestRate, totalLoanAmount, annuity, startDate.plusMonths(durationMonths),startDate);

        //Create RepaymentList for Loan
        
        createRepaymentList(loan);
        
        //saving Loan object
        
        loanRepository.save(loan);

        return loan;
    }

    private void createRepaymentList(Loan loan){

        /*get method costs a new Object because escaping references,
          better to not to do it in the loop for loan.getDuration() times*/

        //initializing outstanding principal

        double outstandingPrincipal = loan.getTotalLoanAMount();

        //initializing interest rate

        double nominalInterestRate = loan.getNominalInterestRate();

        //initializing annuity

        double annuity = loan.getAnnuity();

        //initializing first paying date

        LocalDate startDate = loan.getStartDate();


        //filling up RepaymentList with Repayments (every month gives us a value)

        for(int i = 0; i < loan.getDuration(); i++){

            //outstandingPrincipal is still initial, we didn't subtract annuity

            double initialOutstandingPricipal = outstandingPrincipal;

            //interest

            double interest = (nominalInterestRate*NUMBER_OF_DAYS_IN_MONTH*initialOutstandingPricipal)
                                                    /NUMBER_OF_DAYS_IN_YEAR;

            //principal

            double principal;

                //last payment -> principal = IOP (tasksheet only said: interest<=IOP, bt it doesn t make sense, ask them!!!!)

                if(interest<=initialOutstandingPricipal-annuity) {
                    principal = annuity - interest;
                } else {
                    principal = initialOutstandingPricipal;
                }

            //before next turn, distract principal for IOP

            outstandingPrincipal -= principal;

            //creating Repayment
            
            Repayment repayment = new Repayment(startDate.plus(i, ChronoUnit.MONTHS),
                    annuity,
                    principal,
                    interest,
                    initialOutstandingPricipal,
                    outstandingPrincipal);
            
            //saving object
            
            repaymentRepository.save(repayment);
            
            //adding Repayment to Loan's repaymentList
            
            loan.getRepaymentList().add(repayment);
            
            //saving updated loan object
            
            loanRepository.save(loan);

        }

    }

}
