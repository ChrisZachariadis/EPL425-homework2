package cy.ac.ucy.cs.epl425.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.ac.ucy.cs.epl425.LMS.model.Leave;
import cy.ac.ucy.cs.epl425.LMS.service.LeaveService;

@RequestMapping("/api")
@RestController
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    // Simple GET request to fetch all leaves
    @GetMapping("/leaves")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/leaves/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable("id") Long id) {
        Leave laeve = leaveService.getLeaveById(id);

        if (laeve != null) {
            return new ResponseEntity<>(laeve, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/leaves")
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
    try {
        Leave _leave = leaveService.saveLeave(new Leave(leave.getEmployeeId(), leave.getDescription(), leave.getStartDate(), leave.getEndDate(), leave.getApproved()));
    return new ResponseEntity<>(_leave, HttpStatus.CREATED);
    } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @PutMapping("/leaves/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable("id") Long id, @RequestBody Leave leave) {
        Leave _leave = leaveService.getLeaveById(id);

        if (_leave != null) {
            _leave.setEmployeeId(leave.getEmployeeId());
            _leave.setDescription(leave.getDescription());
            _leave.setStartDate(leave.getStartDate());
            _leave.setEndDate(leave.getEndDate());
            _leave.setApproved(leave.getApproved());
            return new ResponseEntity<>(leaveService.saveLeave(_leave), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/leaves")
    public ResponseEntity<HttpStatus> deleteAllLeave() {
        try {
            leaveService.deleteAllLeave();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/leaves/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        try {
            leaveService.deleteLeaveById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
