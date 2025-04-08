package cy.ac.ucy.cs.epl425.LMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cy.ac.ucy.cs.epl425.LMS.model.Employee;



@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    // Define custom finder methods
    // ============================
    // returns all Employee entities whose firstname column contains input val.
    public Iterable<Employee> findByFirstnameContaining(String firsname);
    // returns all Employee entities whose lastname column has the value val.
    Iterable<Employee> findByLastnameContaining(String lastname);
    // returns all Employee entities whose department column has the value val.
    Iterable<Employee> findByDepartment(String depatment);


}
