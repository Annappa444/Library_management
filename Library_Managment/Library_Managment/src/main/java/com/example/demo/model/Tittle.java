package com.example.demo.model;

import java.io.Serializable;

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
public class Tittle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int tittleId;
	    private  String tittleName;
	    private  String tittleType;
	    private boolean isAvailable;
	    private int totalcopies;
		
	    @JsonBackReference
	    @OneToOne
	    @JoinColumn(name="Loan_ID")
		 private Loan loan;

		public Tittle(String tittleName, String tittleType) {
			super();
			this.tittleName = tittleName;
			this.tittleType = tittleType;
		}
		
		
	    

}
