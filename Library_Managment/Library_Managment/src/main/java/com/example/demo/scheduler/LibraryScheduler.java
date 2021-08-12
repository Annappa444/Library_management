package com.example.demo.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.Loan;
import com.example.demo.service.LibraryService;

@Component
public class LibraryScheduler {

	@Autowired
	LibraryService libraryService;
	
	@Scheduled(cron="0 */1 * * * ?")
	public void getOverdueLoans()
	{
		List<Loan> overdueLoans=libraryService.findOverDue();
		if(overdueLoans==null)
		{
			System.out.println(new Date().toString()+"OverdueLoans empty....");
		}
		else
		{
			System.out.println(new Date().toString()+"OverdueLoans list is not empty and size is:"+overdueLoans.size());
		}
		
	}
}

