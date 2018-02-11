package ernest.giorgi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ernest.giorgi.domain.Loan;

public interface LoanRepository extends CrudRepository<Loan, Integer>{
	
	public List<Loan> findAll();
	
	public Loan findById(int id);

}
