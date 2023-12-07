package com.br.backend.dto;

import com.br.backend.model.UserRole;

public record RegisterRecordDTO(String userSystem, String email, String password, UserRole role, String name) {
    
}
