package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Borrower;
import com.example.demo.model.Lendloan;
import com.example.demo.model.Loan;
import com.example.demo.model.Tittle;
import com.example.demo.repository.BorrowerRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.TitleRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class LibraryManagmentApplicationTests {


	@Autowired
	private TitleRepository titleRepository;
	@Autowired
	private BorrowerRepository borrowerRepository;
	@Autowired
	private LoanRepository loanRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void addTitleCd()
	{
		Tittle title= new Tittle();
		title.setTittleName("Sriganesha");
		title.setTittleType("cd");
		title.setTotalcopies(20);
		title.setAvailable(true);
		titleRepository.save(title);
		
		assertNotNull(titleRepository.findById(1).get());
				
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void addTitleBook()
	{
		Tittle title= new Tittle();
		title.setTittleName("bhagavathe");
		title.setTittleType("book");
		title.setTotalcopies(30);
		title.setTotalcopies(20);
		titleRepository.save(title);
		
		assertNotNull(titleRepository.findById(2).get());
		
		
	}

	@Test
	@Order(3)
	@Rollback(value = false)
	public void addTitleDVD()
	{
		Tittle title= new Tittle();
		title.setTittleName("yakshagana");
		title.setTittleType("DVD");
		title.setTotalcopies(20);
		title.setTotalcopies(30);
		titleRepository.save(title);
		
		assertNotNull(titleRepository.findById(3).get());
		
		
	}
	@Test
	@Order(12)
	@Rollback(value = false)
	public void registerBorrower()
	{
		Borrower borrower= new Borrower();
		borrower.setBorrowerName("Krishna");
		borrowerRepository.save(borrower);
		assertNotNull(titleRepository.findById(2).get());
		
		
	}
	
	@Order(4)
	@Test
	public void findavailableTitlesSuccess()
	{
		List<Tittle> title= titleRepository.findAll();
		System.out.println(title);
		List<Tittle> availableTitle= new ArrayList<>();
		for(Tittle t:title)
		{
			if(t.getTotalcopies()>0) {
				availableTitle.add(t);
			}
		}
		System.out.println("available:  "+availableTitle);
		assertThat(availableTitle).size().isGreaterThan(0);
		
	}
  
	@Order(5)
	@Test
	public void findavailableTilesFailure()
	{
		List<Tittle> title= titleRepository.findAll();
		System.out.println(title);
		List<Tittle> availableTitle= new ArrayList<>();
		for(Tittle t:title)
		{
			if(t.getTotalcopies()>0) {
				availableTitle.add(t);
			}
		}
		assertThat(availableTitle).size().isGreaterThan(4);
		
	}

	
	
	
	@Order(6)
	@Test
	@Rollback(value = false)
	public void lendTitlesSuccess()
	{
		LocalDateTime takenOut= LocalDateTime.now();
		LocalDateTime   dueBack = takenOut.plusDays(7);
		Lendloan lendloan= new Lendloan();
        lendloan.setTotalcopieslended(20);
        lendloan.setTakenOut(takenOut);
        lendloan.setTittleId(1);
        Borrower borrower = new Borrower();
        borrower.setBorrowerName("shridhar");
        
        Tittle title= titleRepository.findById(lendloan.getTittleId()).get();
        
        int availablecopies=title.getTotalcopies();
   	    Loan loan= new Loan();
        if(lendloan.getTotalcopieslended()<= availablecopies)
        {
        loan.setBorrower(borrower);
        loan.setTitle(title);
        loan.setLoanReturnId(0);
        loan.setDueBack(dueBack);
        loan.setTakenOut(takenOut);
        loan.setTotalCopieslended(lendloan.getTotalcopieslended());
        borrower.setLoan(loan);
        title.setLoan(loan);
        title.setTotalcopies(availablecopies-lendloan.getTotalcopieslended());
        if(title.getTotalcopies()>0)
        {
        	title.setAvailable(true);
        }
        loanRepository.save(loan);
        titleRepository.save(title);
        borrowerRepository.save(borrower); 
        assertNotNull(loanRepository.findById(4).get());
    
        }
      	}
	
	@Order(7)
	@Test
	public void findAvailableTitlesAfterLoan()
	{
		List<Tittle> title= titleRepository.findAll();
		List<Tittle> availableTitle= new ArrayList<>();
		for(Tittle t:title)
		{
			if(t.getTotalcopies()>0) {
				availableTitle.add(t);
			}
		}
		System.out.println("after loan"+availableTitle);
		assertThat(availableTitle).size().isGreaterThan(0);
		
	}
	
	@Order(8)
	@Test
	public void findLoanList()
	{
		List<Loan> loan= loanRepository.findAll();
	
		List<Loan> availableLoan= new ArrayList<>();
		for(Loan t:loan)
		{
			
			availableLoan.add(t);
			
		}
	
		assertThat(availableLoan).size().isGreaterThan(0);
		
	}
	
	@Order(11)
	@Test
	@Rollback(value = false)
	public void lendTitlesFailurebyNonAvailabilityOfRequestedCopies()
	
	{
		LocalDateTime takenOut= LocalDateTime.now();
		LocalDateTime   dueBack = takenOut.plusDays(7);
		Lendloan lendloan= new Lendloan();
        lendloan.setTotalcopieslended(90);
        lendloan.setTakenOut(takenOut);
        lendloan.setTittleId(1);
        Borrower borrower = new Borrower();
        borrower.setBorrowerName("shridhar");
        
        Tittle title= titleRepository.findById(lendloan.getTittleId()).get();
        
        int availablecopies=title.getTotalcopies();
   	    Loan loan= new Loan();
        if(lendloan.getTotalcopieslended()< availablecopies)
        {
        loan.setBorrower(borrower);
        loan.setTitle(title);
        loan.setLoanReturnId(0);
        loan.setDueBack(dueBack);
        loan.setTakenOut(takenOut);
        loan.setTotalCopieslended(lendloan.getTotalcopieslended());
        borrower.setLoan(loan);
        title.setLoan(loan);
        title.setTotalcopies(availablecopies-lendloan.getTotalcopieslended());
        if(title.getTotalcopies()>0)
        {
        	title.setAvailable(true);
        }
        loanRepository.save(loan);
        titleRepository.save(title);
        borrowerRepository.save(borrower);
       
        assertNotNull(loanRepository.findById(5).get());
        
        }
         
	
	}
	@Order(9)
	@Test
	@Rollback(value = false)
	public void returnItemSuccess() {

		int loanId=4;
		Loan loan=loanRepository.findById(loanId).get();
		int tittleIdReturned= loan.getTitle().getTittleId();
		
		Tittle titlereturned= titleRepository.findById(tittleIdReturned).get();
		titlereturned.setAvailable(true);
		titlereturned.setTotalcopies(loan.getTotalCopieslended()+titlereturned.getTotalcopies());
		loan.setTotalCopieslended(loan.getTotalCopieslended()-titlereturned.getTotalcopies());
		loan.setLoanReturnId(1);
		loan.setOverdue(false);
		titleRepository.save(titlereturned);
		loanRepository.save(loan);
		assertNotNull(loanRepository.findById(loanId).get().getLoanReturnId());
	
}

	
	@Order(10)
	@Test
	public void findOverDue() {

		LocalDateTime today= LocalDateTime.now();
		List<Loan> overdueLoans= new ArrayList<Loan>();
		List<Loan> loans=loanRepository.findAll();
		for(Loan loan: loans )
		{
		LocalDateTime dueback= loan.getDueBack();
		boolean isBefore = dueback.isBefore(today);
		if(isBefore)
		{
			loan.setOverdue(true);
			overdueLoans.add(loan);
			loanRepository.save(loan);
		}
		}
		System.out.println("overdue Loans:  "+overdueLoans);
		assertThat(overdueLoans).size().isGreaterThan(0);
		
	
	
}
}
