package com.br.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.backend.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserDetails findByUserSystem(String userSystem);

    UserDetails findByEmail(String email);
}
