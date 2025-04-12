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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.ac.ucy.cs.epl425.LMS.model.Leave;
import cy.ac.ucy.cs.epl425.LMS.service.LeaveService;

@RequestMapping("/api")
@RestController
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    // GET /api/leaves?startDate=xxx&endDate=yyy&approved=true
    @GetMapping("/leaves")
    public ResponseEntity<List<Leave>> getAllLeaves(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Boolean approved) {

        List<Leave> result = leaveService.getFilteredLeaves(startDate, endDate, approved);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST /api/leaves/employees/{employeeId}
    @PostMapping("/leaves/employees/{employeeId}")
    public ResponseEntity<Leave> createLeave(@PathVariable Long employeeId, @RequestBody Leave leave) {
        try {
            Leave _leave = new Leave();
            _leave.setEmployeeId(employeeId);
            _leave.setDescription(leave.getDescription());
            _leave.setStartDate(leave.getStartDate());
            _leave.setEndDate(leave.getEndDate());
            _leave.setApproved(leave.getApproved());

            Leave savedLeave = leaveService.saveLeave(_leave);
            return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
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
