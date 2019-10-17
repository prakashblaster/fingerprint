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

	public String addEmployee(Optional<Employee> employee) {
		Employee employee2 = employee.get();
		employeeRepository.save(employee2);

		String mapString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/employees/")
				.path("/" + employee2.getNo() + "/").toUriString();
		String mapAll = ServletUriComponentsBuilder.fromCurrentContextPath().path("/employees/").toUriString();

		return "employee added successfully" + "\n" + "Current Employee" + " " + ":" + " " + mapString + "\n"
				+ "All Employees" + " " + ":" + " " + mapAll;
	}

	public String updateName(int no, String name, Optional<Employee> employee) {
		Employee emp = employeeRepository.findById(no).get();
		if (emp != null) {
			emp.setName(name);
			employeeRepository.saveAndFlush(emp);
		} else {
			throw new RuntimeException("employee not found");
		}
		return "updated successfully";
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmp(int no, Employee emp) {
		Employee employee = employeeRepository.findById(no).get();
		return employee;
	}

}
