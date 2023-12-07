package com.br.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Table(name = "customers")
@Data
@Entity
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(unique = true) 
    private String cpf;
    @Column(unique = true) 
    private String email;
    private String birthday;
}
