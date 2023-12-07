package com.br.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.backend.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    // Você pode adicionar métodos específicos do repositório, se necessário
}
