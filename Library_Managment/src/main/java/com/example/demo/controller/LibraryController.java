package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Borrower;
import com.example.demo.model.Lendloan;
import com.example.demo.model.Loan;
import com.example.demo.model.TitleCreateRequest;
import com.example.demo.model.Tittle;
import com.example.demo.service.LibraryService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LibraryController {

	@Autowired
	LibraryService libraryService;
    
	@ApiOperation(value = "Get All avaliable Inventory", notes = "returns all tiles details")
	@RequestMapping(value = "/avaliableTitles")
	public List<Tittle> getCurrentInventory() {
		return libraryService.findAvailable();
	}
    
	@ApiOperation(value = "Get All overdue loans", notes = "returns all overdue loan details")
	@RequestMapping(value = "/findOverdueTitles")
	public List<Loan> getOverdueLoans() {
		return libraryService.findOverDue();
	}
	@ApiOperation(value = "lends item on loans to borrrower", notes = "returns  loan details")
	@RequestMapping(value = "/lendTitles", method = RequestMethod.POST)
	public Loan lend(@RequestBody Lendloan lendloan) {
		return libraryService.lendTitles(lendloan);

	}
	@ApiOperation(value = "return back lended tiles to library ", notes = "returns  nothing")
	@RequestMapping(value = "/returnItem/{loanId}", method = RequestMethod.POST)
	public void returnItem(@PathVariable int loanId) {
		libraryService.returnItem(loanId);

	}
	@ApiOperation(value = "register the borrower ", notes = "returns  borrower details")
	@RequestMapping(value = "/registerBorrower", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

	public Borrower registerBorrower(@RequestBody Borrower borrower) {

		return libraryService.register(borrower);
	}

	@ApiOperation(value = "adds tittles to library inventory ", notes = "returns added tittle  details")
	@RequestMapping(value = "/addTittleItem", method = RequestMethod.POST)
	public Tittle addTittleItem(@RequestBody TitleCreateRequest titleCreateRequest) {
		return libraryService.addTittles(titleCreateRequest.getTittleName(), titleCreateRequest.getTittleType(),
				titleCreateRequest.getTotalcopiestobeAdded());
	}
	
	@ApiOperation(value = "borrowed titles for a user ", notes = "returns  tittle  details")
	@RequestMapping(value = "/getTittles/{borrowerID}")
	public Tittle getTitlesByBorrower(@PathVariable  int  borrowerID) {
		return libraryService.getTitlesByBorrower(borrowerID);
	}

}
