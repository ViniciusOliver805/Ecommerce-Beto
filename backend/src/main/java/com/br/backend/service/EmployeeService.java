package com.br.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.br.backend.dto.EmployeeDTO;
import com.br.backend.model.EmployeeModel;
import com.br.backend.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Salvar Funcionário
    public EmployeeModel saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeDTO, employeeModel);
        return employeeRepository.save(employeeModel);
    }

    //Listar todos os funcionários
    public List <EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Buscar dados do funcionário por id
    public Optional<EmployeeModel> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    //Atualizar dados do funcionário por id
    public EmployeeModel updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeModel> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeModel employeeModel = optionalEmployee.get();
            BeanUtils.copyProperties(employeeDTO, employeeModel);
            return employeeRepository.save(employeeModel);
        }
        return null;
    }

    //Deletar dados do funcionário por id
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}
