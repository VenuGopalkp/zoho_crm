package com.zohocrm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zohocrm.entities.Lead;
import com.zohocrm.repositories.LeadRepositiry;

@Service
public class LeadServiceImpl implements LeadService {
	
	@Autowired
private LeadRepositiry leadRepo;
	
	@Override
	public void saveLead(Lead lead) {
		
		leadRepo.save(lead);  //This method we have to call from the Controller layer and create one Interface with one reference variable
		                      // from here saving the data in to the DB
		
	}

	@Override
	public List<Lead> findAllLeads() {
		List<Lead> leadss = leadRepo.findAll();
		return leadss;
		
	}

	

	@Override
	public Lead getLead(long id) {
		Optional<Lead> findById = leadRepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}

	@Override
	public void deleteLead(long id) {
		leadRepo.deleteById(id);
		
	}

}
