package com.br.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.backend.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    
}
