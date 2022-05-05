package com.zohocrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zohocrm.entities.Lead;

public interface LeadRepositiry extends JpaRepository<Lead, Long> {

}
