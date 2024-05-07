package be.intec.service;

import be.intec.model.Employee;
import be.intec.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee)

    {
        this.employeeRepository.save(employee);

    }

    public Employee getEmployeeById(Long id)
    {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = null;
        if(employeeOptional.isPresent())
        {
           employee = employeeOptional.get();

        }
        else
        {
            throw new RuntimeException("Employee not found " + id);
        }
        return employee;

    }

    public void deleteEmployeeById(Long id)
    {
        this.employeeRepository.deleteById(id);
    }

}



