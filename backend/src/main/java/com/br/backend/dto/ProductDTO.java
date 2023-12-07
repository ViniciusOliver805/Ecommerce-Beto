package com.br.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 255, message = "O NOME deve ter apenas 255 Caracteres!")
    private String name;

    @NotNull(message = "O PREÇO do produto não pode estar em branco")
    private Double price;

    @NotBlank(message = "A CATEGORIA do produto não pode estar em branco")
    private String category;

 
}
