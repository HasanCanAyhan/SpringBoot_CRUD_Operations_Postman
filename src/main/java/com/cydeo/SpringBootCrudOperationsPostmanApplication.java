package com.cydeo;

import com.cydeo.model.Employee;
import com.cydeo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudOperationsPostmanApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudOperationsPostmanApplication.class, args);


    }

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Can");
        employee.setLastName("Ayhan");
        employee.setEmailId("c@gmail.com");

        employeeRepository.save(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Ramesh");
        employee1.setLastName("Cena");
        employee1.setEmailId("ramesh@gmail.com");

        employeeRepository.save(employee1);


    }



}
