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

import com.br.backend.dto.EmployeeDTO;
import com.br.backend.model.EmployeeModel;
import com.br.backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Endpoint para SALVAR cadastro do funcionário
    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        if (employeeDTO.getBadge() == null || employeeDTO.getEmail() == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //futuramente adicionar tratamento de exceções nos bad_request
        } 

        EmployeeModel employeeModel = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeModel);
    }

    //Endpoint para CONSULTAR TODOS os cadastros de funcionário
    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        List<EmployeeModel> employeesList = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeesList);
    }
    //Endpoint para CONSULTAR dados do funcionário pelo ID
    @GetMapping("/{idEmployee}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long idEmployee) {
        Optional<EmployeeModel> employeesOptional = employeeService.getEmployeeById(idEmployee);
        if (employeesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employeesOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta dos dados do funcionário pelo ID falhou! Verifique se o ID está correto e tente novamente ");
        }
    }

    //Endpoint para ATUALIZAR dados do funcionário pelo ID 
    @PutMapping ("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeModel updateEmployee = employeeService.updateEmployee(id, employeeDTO);
        if(updateEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado !");
        }
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Funcionário excluido do sistema com sucesso !");
    }
    
    
}
