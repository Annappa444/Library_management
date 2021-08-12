package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Loan implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int loanId;
	
	 @JsonManagedReference("borrower")
	 @OneToOne( cascade = CascadeType.ALL)
	 @JoinColumn(name="borrowerId")
	  private Borrower borrower;
	 

    private  LocalDateTime takenOut;
    private  LocalDateTime dueBack;
    private int totalCopieslended;
    private boolean isOverdue;
    @JsonManagedReference("title")
	 @OneToOne( cascade = CascadeType.ALL)
	 @JoinColumn(name="titleID")
    private Tittle title;
    private int LoanReturnId;
    

}
