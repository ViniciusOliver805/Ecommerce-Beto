package com.br.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    @Size(max = 255, message = "O NOME deve ter apenas 255 Caracteres!")
    @NotBlank(message = "O NOME não pode estar em branco")
    private String name;
    @NotBlank(message = "O CPF não pode estar em branco")
    private String cpf;
    @Email(message = "O formato do email é inválido!")
    @Size(max = 255, message = "O EMAIL deve ter apenas 255 Caracteres!")
    @NotBlank
    private String email;
    @NotBlank
    private String birthday;
}
