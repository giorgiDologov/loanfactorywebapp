package ernest.giorgi.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ernest.giorgi.repository.LoanRepository;
import ernest.giorgi.service.LoanFactory;

@Controller
public class LoanController {
	
	@Autowired
	private LoanFactory loanFactory;
	
	@Autowired
	private LoanRepository loanRepository;

    @RequestMapping(value="loan/new", method=RequestMethod.GET)
    public String newLoanGet(Model model){
        return "loanform";
    }
    
    @RequestMapping(value="loan/new", method=RequestMethod.POST)
    public String newLoanPost(@ModelAttribute("totalLoanAmount") double totalLoanAmount,
    							@ModelAttribute("nominalInterestRate") double nominalInterestRate,
    							@ModelAttribute("durationMonths") int durationMonths,
    							@ModelAttribute("startDate") String startDate,
    							Model model){
    	
    	loanFactory.createLoan(durationMonths, nominalInterestRate, totalLoanAmount, LocalDate.parse(startDate));
    	
        return "redirect:/loans";
    }
    
    @RequestMapping(value="loans")
    public String allLoans(Model model){
    	
    	model.addAttribute("loanList", loanRepository.findAll());
    	
        return "loans";
    }
    
    @RequestMapping(value="loanDetails")
    public String loanDetails(@RequestParam("id") int id, Model model){
    	
    	model.addAttribute("repaymentList", loanRepository.findById(id).getRepaymentList());
    	
        return "loanshow";
    }
    

}
