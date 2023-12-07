package com.br.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.backend.dto.CustomerDTO;
import com.br.backend.model.CustomerModel;
import com.br.backend.service.CustomerService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("customer")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Endpoint para SALVAR cadastro de cliente
    @PostMapping
    public ResponseEntity<CustomerModel> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        if (customerDTO.getName() == null || customerDTO.getCpf() == null || customerDTO.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        CustomerModel customerModel = customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerModel);
    }


    //Endpoint para CONSULTAR todos os cadastro de cliente
    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        List<CustomerModel> customerList = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }


    //Endpoint para CONSULTAR POR ID clientes
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable String id) {
        Optional<CustomerModel> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Consulta dos dados do cliente por ID falhou! Verifique se o ID está correto e tente novamente");
        }
    }


    //Endpoint para ATUALIZAR os dados do cliente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerDTO customerDTO) {
        CustomerModel updateCustomer = customerService.updateCustomer(id, customerDTO);
        if (updateCustomer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado !");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido do sistema com sucesso !");
    }

}
