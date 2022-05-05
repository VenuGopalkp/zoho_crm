package com.zohocrm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zohocrm.entities.Contact;
import com.zohocrm.entities.Lead;
import com.zohocrm.services.ContactService;
import com.zohocrm.services.LeadService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadservice;  //Interface name and creating its object
	
	@Autowired
	private ContactService contactservice; 
	
	@RequestMapping("/view")
	public String viewCreateLeadPage() {  //I have created one method
		return "create_lead";
			
	}

	@RequestMapping("/saveLead")
	public String saveoneLead(@ModelAttribute("led") Lead lead,ModelMap model) { 
		leadservice.saveLead(lead);
		model.addAttribute("lead",lead); 
		return "lead_info";
		
		
		
	}
	
	@RequestMapping("/listallleads")
	public String listAllLeads(ModelMap model) {
		 List<Lead> leadss = leadservice.findAllLeads();
		model.addAttribute("leads",leadss);
		return "lead_result";
		
		
		
	}
	
	@RequestMapping("/getLeadById")
	public String getLeadById(@RequestParam("id") long id,ModelMap model) {
	 Lead lead = leadservice.getLead(id);
	 model.addAttribute("lead",lead);
	 return "lead_info";
	
		
		
	}
	
	@RequestMapping("/convertLead")
	public String convertLead(@RequestParam("id") long id,ModelMap model) {
		Lead lead = leadservice.getLead(id); 
		 
		 Contact contact = new Contact();
		 contact.setFirstName(lead.getFirstName()); //fetch the first name from leads table
		 contact.setLastName(lead.getLastName());
		 contact.setEmail(lead.getEmail());
		 contact.setLeadSource(lead.getLeadSource());
		 contact.setMobile(lead.getMobile());
		 
		 contactservice.saveContact(contact);
		 
		 leadservice.deleteLead(id);
		 
		 List<Contact> contacts = contactservice.findAllContacts();
		 model.addAttribute("contacts",contacts);
		 
		 return "contact_result";
		
		
	}
	
}
