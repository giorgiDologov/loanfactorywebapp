package ernest.giorgi.repositories;

import ernest.giorgi.configuration.RepositoryConfiguration;
import ernest.giorgi.domain.Loan;
import ernest.giorgi.repository.LoanRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class LoanRepositoryTest {

	@Autowired
    private LoanRepository loanRepository;

    @Test
    public void testSaveLoan(){
        //setup loan
        Loan loan = new Loan(24,5,5000,219,LocalDate.now(), LocalDate.now().plusMonths(24));

        //save loan, verify has ID value after save
        loanRepository.save(loan);
        assertNotNull(loan.getId()); //not null after save
        //fetch from DB
        Loan fetchedLoan = loanRepository.findOne(loan.getId());

        //should not be null
        assertNotNull(fetchedLoan);

        //should equal
        assertEquals(loan.getId(), fetchedLoan.getId());
        assertEquals(loan.getDuration(), fetchedLoan.getDuration());

        //we won't update, because we made it impossible, only LoanFactory can update

        //verify count of products in DB
        long loanCount = loanRepository.count();
        assertEquals(loanCount, 1);

        //get all products, list should only have one
        Iterable<Loan> loans = loanRepository.findAll();

        int count = 0;

        for(Loan l : loans){
            count++;
        }

        assertEquals(count, 1);
    }
}
