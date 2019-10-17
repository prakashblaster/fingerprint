package in.dotworld.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.dotworld.model.Employee;
import in.dotworld.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public String addEmployee(Employee employee) {
		
		employeeRepository.save(employee);

		String mapString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/employees/")
				.path("/" + employee.getNo()).toUriString();
		String mapAll = ServletUriComponentsBuilder.fromCurrentContextPath().path("/employees/").toUriString();

		return "employee added successfully" + "\n" + "Current Employee" + " " + ":" + " " + mapString + "\n"
				+ "All Employees" + " " + ":" + " " + mapAll;
	}

	public String updateName(int no, Employee employee) {
		Employee emp = employeeRepository.findById(no).get();
		if (emp != null) {
			employeeRepository.saveAndFlush(employee);
		} else {
			throw new RuntimeException("employee not found");
		}
		String linkString=ServletUriComponentsBuilder.fromCurrentContextPath().path("/employees/")
				.path("/"+emp.getNo()).toUriString();
		return "updated successfully" +"\n"+"view updated employee"+" "+":"+" "+linkString;
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmp(int no, Employee emp) {
		Employee employee = employeeRepository.findById(no).get();
		return employee;
	}

}
