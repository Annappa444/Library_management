package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Borrower {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	   private int borrowerId;
	    private  String borrowerName;
	    
	    @JsonBackReference("borrower")
	    @JoinColumn(name="borrower_id")
	    @OneToOne
	    private Loan loan;
	   
	    
	    

}
