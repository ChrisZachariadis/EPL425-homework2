package cy.ac.ucy.cs.epl425.LMS.repository;

import org.springframework.data.repository.CrudRepository;

import cy.ac.ucy.cs.epl425.LMS.model.Leave;

public interface LeaveRepository extends CrudRepository<Leave, Long> {
    // Define custom finder methods
    // ============================
    // returns all Leave entities whose employee_id column has the value val.
    Iterable<Leave> findByEmployeeId(Long val);
    // returns all Leave entities whose approved column has the value val.
    Iterable<Leave> findByApproved(Boolean val);
    
}
