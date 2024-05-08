package be.intec.controller;

import be.intec.model.Employee;
import be.intec.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

  /*  @GetMapping("/")
   public String viewHomePage(Model model)
    {
        model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return "index";
    }*/

    @GetMapping({"/showEmployees","/","/list"})
    public ModelAndView showEmployees()
    {
        ModelAndView mav = new ModelAndView("index");
        List<Employee> list = employeeService.getAllEmployees();
        mav.addObject("listEmployees",list);
        return mav;
    }


  /*  @GetMapping("/showNewEmployeeForm")
      public String showNewEmployeeForm(Model model)
    {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }*/
  @GetMapping("/showNewEmployeeForm")
   public ModelAndView showNewEmployeeForm()
    {
        ModelAndView mav = new ModelAndView("new_employee");
        Employee newEmployee = new Employee();
        mav.addObject("employee",newEmployee);
        return mav;
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }



  @GetMapping("/showFormForUpdate")
    public ModelAndView showUpdateForm(@RequestParam Long id)
    {
        ModelAndView mav = new ModelAndView("new_employee");
        Employee employee = employeeService.getEmployeeById(id);
        mav.addObject("employee",employee);
        return mav;
    }


  /*  @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") Long id, Model model) {
        //get employee from the services
        Employee employee = employeeService.getEmployeeById(id);
        //set employee as a model attribute to preopulate the form
        model.addAttribute("employee",employee);
        return  "update_employee";
    }*/



    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long id)
    {
        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }


  /*  @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id)
    {
        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }*/






}
