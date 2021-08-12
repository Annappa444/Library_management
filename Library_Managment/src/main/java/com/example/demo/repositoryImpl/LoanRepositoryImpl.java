package com.example.demo.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepository;

@Component
public class LoanRepositoryImpl {
	@Autowired
	private LoanRepository loanRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unused")
    public List<Loan> findByIdBorrowerID(int id) {
        String hql = "SELECT loan_id from Loan l WHERE e.borrower_id = :id";
        TypedQuery<Loan> query = entityManager.createQuery(hql, Loan.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
