package ernest.giorgi.repositories;

import ernest.giorgi.configuration.RepositoryConfiguration;
import ernest.giorgi.domain.Repayment;
import ernest.giorgi.repository.RepaymentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class RepaymentRepositoryTest {

	@Autowired
    private RepaymentRepository repaymentRepository;

    @Test
    public void testSaveRepayment(){
        //setup repayment
        Repayment repayment = new Repayment(LocalDate.now(),219,20,199,5000, 4781);

        //save repayment, verify has ID value after save
        repaymentRepository.save(repayment);
        assertNotNull(repayment.getId()); //not null after save
        //fetch from DB
        Repayment fetchedRepayment = repaymentRepository.findOne(repayment.getId());

        //should not be null
        assertNotNull(fetchedRepayment);

        //should equal
        assertEquals(repayment.getId(), fetchedRepayment.getId());

        //we won't update, because we made it impossible, only RepaymentFactory can update

        //verify count of products in DB
        long repaymentCount = repaymentRepository.count();
        assertEquals(repaymentCount, 1);

        //get all products, list should only have one
        Iterable<Repayment> repayments = repaymentRepository.findAll();

        int count = 0;

        for(Repayment l : repayments){
            count++;
        }

        assertEquals(count, 1);
    }
}
