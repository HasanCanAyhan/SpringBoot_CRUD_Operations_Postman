package com.cydeo.controller;


import com.cydeo.exception.ResourceNotFoundException;
import com.cydeo.model.Employee;
import com.cydeo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //includes @ResponseBody
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //build create employee rest api

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){ // @RequestBody converting JSON TO JAVA OBJECT
        return employeeRepository.save(employee);
    }


    //build get employee by id rest api
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id:" + id));

        return ResponseEntity.ok(employee);
    }


    //build update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id:" + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);

    }

    //build delete employee rest api

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id){

        Employee deleteEmployee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id:" + id));

        employeeRepository.delete(deleteEmployee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
