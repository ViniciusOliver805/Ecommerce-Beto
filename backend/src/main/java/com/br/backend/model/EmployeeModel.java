package com.br.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Table(name = "employees") // é o nome da tabela no banco
@Data // para geração automatica de get e set
@Entity // para gerar a tabela no banco
public class EmployeeModel {
    @Id //indica que a variavel debaixo é onde iremos armazenar o id gerado
    @GeneratedValue(strategy = GenerationType.IDENTITY) //faz gera automatico o id sequencial
    private Long idEmployee;
    private String name;
    private Integer badge; //crachá
    private String department; // departamento/setor
    @Column(unique = true) // nao permite o email senha duplicado ou seja ter dois igual no banco
    private String email;
    
}
