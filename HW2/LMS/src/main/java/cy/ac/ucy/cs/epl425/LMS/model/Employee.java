package cy.ac.ucy.cs.epl425.LMS.model;

import java.time.LocalDate; // Add this import

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("employees")
public class Employee {

    @Id
	private Long id;

    @Column("firstname")
	private String firstname;

	@Column("lastname")
	private String lastname;

    @Column("department")
	private String department;

    @Column("date_of_birth")
    private LocalDate dateOfBirth;

    public Employee () {

    }

    public Employee (String firstname, String lastname, String department, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters

    public Long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public String getDepartment () {
        return department;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", department=" + department + ", dateOfBirth=" + dateOfBirth + "]";
    }



}
