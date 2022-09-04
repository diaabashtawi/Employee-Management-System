package com.emp.main.service;


import com.emp.main.entity.Employee;
import com.emp.main.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private EmployeesRepository employeesRepository;

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public void saveEmployee(Employee employee){
        employeesRepository.save(employee);
    }

    public List<Employee> getAllEmployess(){
        return employeesRepository.findAll();
    }

    public Employee getEmployeeById(long id){
        Optional<Employee> employee =
                employeesRepository.findById(id);

        if (employee.isPresent()){
            return employee.get();
        }
        return null;
    }

    public void deleteEmployee(long id){
        employeesRepository.deleteById(id);
    }
}
