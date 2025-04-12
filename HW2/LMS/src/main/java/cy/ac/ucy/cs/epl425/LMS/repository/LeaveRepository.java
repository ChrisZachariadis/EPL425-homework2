package cy.ac.ucy.cs.epl425.LMS.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cy.ac.ucy.cs.epl425.LMS.model.Leave;

public interface LeaveRepository extends CrudRepository<Leave, Long> {
    // Define custom finder methods
    // ============================
    // returns all Leave entities whose employee_id column has the value val.
    Iterable<Leave> findByEmployeeId(Long val);

    // returns all Leave entities whose approved column has the value val.

    List<Leave> findByStartDateAfterAndEndDateBefore(LocalDate start, LocalDate end);

    List<Leave> findByStartDateAfterAndEndDateBeforeAndApproved(LocalDate start, LocalDate end, Boolean approved);

    Iterable<Leave> findByStartDateAfter(LocalDate date);

    Iterable<Leave> findByEndDateBefore(LocalDate date);

    List<Leave> findByApproved(Boolean approved);

    List<Leave> findByStartDateGreaterThanEqual(LocalDate date);

    List<Leave> findByEndDateLessThanEqual(LocalDate date);

    List<Leave> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate start, LocalDate end);

    List<Leave> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndApproved(LocalDate start, LocalDate end,
            Boolean approved);

}
