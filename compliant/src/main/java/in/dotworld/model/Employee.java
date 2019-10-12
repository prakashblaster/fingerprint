package in.dotworld.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "EMPLOYEE_TABLE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;

	private String name;
	private String designation;
	private long salary;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lasDate;

	public Employee() {

	}

	public Employee(int no, String name, String designation, long salary, Date createDate, Date lasDate) {
		super();
		this.no = no;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.createDate = createDate;
		this.lasDate = lasDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLasDate() {
		return lasDate;
	}

	public void setLasDate(Date lasDate) {
		this.lasDate = lasDate;
	}

}
