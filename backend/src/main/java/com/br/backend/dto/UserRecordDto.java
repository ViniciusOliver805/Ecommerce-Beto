package com.br.backend.dto;

import com.br.backend.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRecordDto {
    private Long idUser;

    @Size(max = 20, message = "O limite para o campo 'Usuário' é de 20 caracteres")
    private String userSystem;

    @Email(message = "Por favor, insira um endereço de e-mail válido")
    private String email;
    private String name;
    private String password;
    private UserRole role;
    
}
