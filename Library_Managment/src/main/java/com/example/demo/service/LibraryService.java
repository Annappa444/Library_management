package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Borrower;
import com.example.demo.model.Lendloan;
import com.example.demo.model.Loan;
import com.example.demo.model.Tittle;
import com.example.demo.repository.BorrowerRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.TitleRepository;

@Service
public class LibraryService {
	@Autowired
	BorrowerRepository borrowerRepository;
	@Autowired
	LoanRepository loanRepository;

	@Autowired
	TitleRepository titleRepository;

	public List<Tittle> findAvailable() {
		List<Tittle> availableTitles = new ArrayList<Tittle>();
		List<Tittle> allTitles = titleRepository.findAll();
		for (Tittle t : allTitles) {
			if (t.getTotalcopies() > 0) {
				availableTitles.add(t);
			}

		}
		return availableTitles;

	}

	public List<Loan> findOverDue() {
		LocalDateTime today = LocalDateTime.now();
		List<Loan> overdueLoans = new ArrayList<Loan>();
		List<Loan> loans = loanRepository.findAll();
		for (Loan loan : loans) {
			LocalDateTime dueback = loan.getDueBack();
			boolean isBefore = dueback.isBefore(today);
			if (isBefore) {
				loan.setOverdue(true);
				overdueLoans.add(loan);
			}
		}
		return overdueLoans;
	}

	public Loan lendTitles(Lendloan lendloan) {
		LocalDateTime takenOut = LocalDateTime.now();
		LocalDateTime dueBack = takenOut.plusDays(7);

		Borrower borrower = new Borrower();
		borrower.setBorrowerName(lendloan.getBorrowerName());
		Tittle title = titleRepository.findById(lendloan.getTittleId()).get();

		int availablecopies = title.getTotalcopies();
		Loan loan = new Loan();
		if (lendloan.getTotalcopieslended() < availablecopies) {
			loan.setBorrower(borrower);
			loan.setTitle(title);
			loan.setLoanReturnId(0);
			loan.setDueBack(dueBack);
			loan.setTakenOut(takenOut);
			loan.setTotalCopieslended(lendloan.getTotalcopieslended());
			borrower.setLoan(loan);
			title.setLoan(loan);
			title.setTotalcopies(availablecopies - lendloan.getTotalcopieslended());
			if (title.getTotalcopies() > 0) {
				title.setAvailable(true);
			}
			loanRepository.save(loan);
			titleRepository.save(title);
			borrowerRepository.save(borrower);
		}
		return loan;

	}

	public void returnItem(int loanId) {

		Loan loan = loanRepository.findById(loanId).get();
		int tittleIdReturned = loan.getTitle().getTittleId();
		Tittle titlereturned = titleRepository.findById(tittleIdReturned).get();
		titlereturned.setAvailable(true);
		titlereturned.setTotalcopies(loan.getTotalCopieslended() + titlereturned.getTotalcopies());
		loan.setTotalCopieslended(loan.getTotalCopieslended()-titlereturned.getTotalcopies());
		loan.setLoanReturnId(loan.getLoanReturnId()+1);
		loan.setOverdue(false);
		titleRepository.save(titlereturned);
		loanRepository.save(loan);

	}

	public Borrower register(Borrower borrower) {
		return borrowerRepository.save(borrower);

	}

	public Tittle addTittles(String tittleName, String tittleType, int totalcopiestobeAdded) {
		Tittle title = new Tittle(tittleName, tittleType);
		title.setAvailable(true);
		title.setTotalcopies(totalcopiestobeAdded);
		titleRepository.save(title);
		return title;
	}

	public Tittle getTitlesByBorrower(int borrowerID) {
		Loan loan=loanRepository.findByIdBorrowerID(borrowerID);
		Tittle assignedTitles= loan.getTitle();
		return assignedTitles;
	}

}