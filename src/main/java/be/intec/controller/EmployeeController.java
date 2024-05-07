package be.intec.controller;

import be.intec.model.Employee;
import be.intec.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller

public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return "index";
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model)
    {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") Long id, Model model) {
        //get employee from the services
        Employee employee = employeeService.getEmployeeById(id);
        //set employee as a model attribute to pre populate the form
        model.addAttribute("employee",employee);
        return  "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id)
    {
        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }






}
