package com.example.cms.model.repository;

import com.example.cms.model.entity.Cruise;
import com.example.cms.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CruiseRepository extends JpaRepository<Cruise, Integer> {
}
