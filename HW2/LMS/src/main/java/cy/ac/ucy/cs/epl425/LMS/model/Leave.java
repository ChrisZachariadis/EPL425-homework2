package cy.ac.ucy.cs.epl425.LMS.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("leaves")
public class Leave {

    @Id
	private Long id;

    @Column("employee_id")
	private Long employeeId;

    @Column("description")
    private String description;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("approved")
    private Boolean approved;


    public Leave () {

    }
    public Leave (Long employeeId, String description, LocalDate startDate, LocalDate endDate, Boolean approved) {
        this.employeeId = employeeId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    // Setters

    public void setEmployeeId(Long employee_id) {
        this.employeeId = employee_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    @Override
    public String toString() {
        return "Leave [id=" + id + ", employee_id=" + employeeId + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", approved=" + approved + "]";
    }



}
