package cy.ac.ucy.cs.epl425.LMS.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.ac.ucy.cs.epl425.LMS.model.Leave;
import cy.ac.ucy.cs.epl425.LMS.repository.LeaveRepository;

@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    public List<Leave> getAllLeaves() {
        List<Leave> leaves = new ArrayList<>();
        this.leaveRepository.findAll().forEach(leaves::add);
        return leaves;
    }
    
    public Leave getLeaveById(Long id) {
        Optional<Leave> leave = this.leaveRepository.findById(id);
        return leave.orElse(null);
    }
        
    public List<Leave> getFilteredLeaves(String startDate, String endDate, Boolean approved) {
        List<Leave> result = new ArrayList<>();
    
        LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null) ? LocalDate.parse(endDate) : null;
    
        if (start != null && end != null && approved != null) {
            result = leaveRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndApproved(start, end, approved);
        } else if (start != null && end != null) {
            result = leaveRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(start, end);
        } else if (start != null) {
            result = leaveRepository.findByStartDateGreaterThanEqual(start);
        } else if (end != null) {
            result = leaveRepository.findByEndDateLessThanEqual(end);
        } else if (approved != null) {
            leaveRepository.findByApproved(approved).forEach(result::add);
        } else {
            leaveRepository.findAll().forEach(result::add);
        }
    
        return result;
    }
    

    public Leave saveLeave(Leave leaves) {
        return this.leaveRepository.save(leaves);
    }

    public void deleteAllLeave() {
        this.leaveRepository.deleteAll();
    }


    public void deleteLeaveById(Long id) {
        this.leaveRepository.deleteById(id);
    }

}
