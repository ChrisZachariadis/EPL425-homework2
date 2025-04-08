package cy.ac.ucy.cs.epl425.LMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cy.ac.ucy.cs.epl425.LMS.repository.EmployeeRepository;


import cy.ac.ucy.cs.epl425.LMS.model.Employee;

@Service
public class EmployeeService {
    @Autowired EmployeeRepository employeeRepository;

       public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        this.employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

       public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if(employee.isPresent())
	        return employee.get();
        else 
            return null;

    }


    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }
}
