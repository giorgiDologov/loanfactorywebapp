package ernest.giorgi.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Loan {

    //connect loan with it's owner by @ManyToOne

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private int duration; //number of instalments in months
    private double nominalInterestRate; //ex.: 5 will be 5%
    private double totalLoanAMount;
    private double annuity;
    private LocalDate startDate; //when we start paying (I did not multiply the total amount by the nominal rate until then)
    private LocalDate dateOfPayout;
    
    @OneToMany
    private List<Repayment> repaymentList = new ArrayList<>();

    public Loan(int duration, double nominalInterestRate, double totalLoanAMount,
                double annuity, LocalDate dateOfPayout, LocalDate startDate) {
        this.duration = duration;
        this.nominalInterestRate = nominalInterestRate;
        this.totalLoanAMount = totalLoanAMount;
        this.annuity = annuity;
        this.dateOfPayout = dateOfPayout;
        this.startDate = startDate;
    }
    
    public Loan() {};

    //no setters to client

    //to avoid escaping references, new Objects to the client

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
        return new Integer(duration);
    }

    public double getNominalInterestRate() {
        return new Double(nominalInterestRate);
    }

    public double getTotalLoanAMount() {
        return new Double(totalLoanAMount);
    }

    public double getAnnuity() {
        return new Double(annuity);
    }

    public LocalDate getDateOfPayout() {
        return dateOfPayout;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<Repayment> getRepaymentList() {
		return repaymentList;
	}

	@Override
    public String toString() {
        return "Loan{" +
                "duration=" + duration +
                ", nominalInterestRate=" + nominalInterestRate +
                ", totalLoanAMount=" + totalLoanAMount +
                ", annuity=" + annuity +
                ", dateOfPayout=" + dateOfPayout +
                '}';
    }
}

