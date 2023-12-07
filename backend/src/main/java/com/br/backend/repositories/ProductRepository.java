package com.br.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.backend.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, String> {
}
