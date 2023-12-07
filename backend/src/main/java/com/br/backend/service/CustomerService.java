package com.br.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.br.backend.dto.CustomerDTO;
import com.br.backend.model.CustomerModel;
import com.br.backend.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Salvar cliente
    public CustomerModel saveCustomer(CustomerDTO customerDTO) {
        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDTO, customerModel);
        return customerRepository.save(customerModel);
    }

    //Listar todos os clientes
    public List <CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Buscar dados do cliente por id
    public Optional<CustomerModel> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    //Atualizar dados do cliente por id
    public CustomerModel updateCustomer( String id, CustomerDTO customerDTO ) {
        Optional<CustomerModel> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            CustomerModel customerModel = optionalCustomer.get();
            BeanUtils.copyProperties(customerDTO, customerModel);
            return customerRepository.save(customerModel);
        }
        return null;
    }

    //Deletar cliente por id
    public void deleteCustomer(String id){
        customerRepository.deleteById(id);
    }

}
