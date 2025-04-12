package cy.ac.ucy.cs.epl425.LMS.service;

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
        List<Leave> leaves = new ArrayList<Leave>();
        this.leaveRepository.findAll().forEach(leaves::add);
        return leaves;
    }

    public Leave getLeaveById(Long id) {
        Optional<Leave> leaves = this.leaveRepository.findById(id);
        if (leaves.isPresent())
            return leaves.get();
        else
            return null;

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
