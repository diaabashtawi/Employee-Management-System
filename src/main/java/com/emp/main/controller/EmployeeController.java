package com.emp.main.controller;


import com.emp.main.entity.Employee;
import com.emp.main.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    private EmployeesService employeesService;

    @Autowired
    public EmployeeController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Employee> employees =
                employeesService.getAllEmployess();
        model.addAttribute("employess", employees);
        System.out.println(employees.toString());
        return "index";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee,
                               HttpSession session){
        System.out.println(employee);

        employeesService.saveEmployee(employee);
        session.setAttribute("message", "Employee Added Successfully ");
        return "redirect:/";

    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable long id,
                                Model model){
        Employee employee =
                employeesService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee,
                                    HttpSession session){
        employeesService.saveEmployee(employee);
        session.setAttribute("message", "Employee Data Updated Successfully");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id,
                                 HttpSession session){
        employeesService.deleteEmployee(id);
        session.setAttribute("message", "Employee Deleted Successfully");
        return "redirect:/";
    }





}
