package com.br.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.backend.model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, String> {
    
}
