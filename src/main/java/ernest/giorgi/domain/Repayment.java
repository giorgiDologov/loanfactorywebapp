package ernest.giorgi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Repayment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private LocalDate planPaymentDate;
    private double annuity;
    private double principal;
    private double interest;
    private double initialOutstandingPrincipal;
    private double remainingOutStandingPrincipal;
    
    @ManyToOne
    private Loan loan;

    public Repayment(LocalDate planPaymentDate, double annuity, double principal, double interest,
                     double initialOutstandingPrincipal, double remainingOutStandingPrincipal) {
        this.planPaymentDate = planPaymentDate;
        this.annuity = annuity;
        this.principal = principal;
        this.interest = interest;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.remainingOutStandingPrincipal = remainingOutStandingPrincipal;
    }
    
    public Repayment() {};

    //no setters to client

    //to avoid escaping references, new Objects to the client

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getPlanPaymentDate() {
        return planPaymentDate;
    }

    public double getAnnuity() {
        return new Double(annuity);
    }

    public double getPrincipal() {
        return new Double(principal);
    }

    public double getInterest() {
        return new Double(interest);
    }

    public double getInitialOutstandingPrincipal() {
        return new Double(initialOutstandingPrincipal);
    }

    public double getRemainingOutStandingPrincipal() {
        return new Double(remainingOutStandingPrincipal);
    }

    public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	@Override
    public String toString() {
        return "Repayment{" +
                "planPaymentDate=" + planPaymentDate +
                ", annuity=" + annuity +
                ", principal=" + principal +
                ", interest=" + interest +
                ", initialOutstandingPrincipal=" + initialOutstandingPrincipal +
                ", remainingOutStandingPrincipal=" + remainingOutStandingPrincipal +
                '}';
    }
}
