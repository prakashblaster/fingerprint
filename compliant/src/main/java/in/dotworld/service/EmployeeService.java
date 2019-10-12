package in.dotworld.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dotworld.model.Employee;
import in.dotworld.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public String addEmployee(Optional<Employee> employee) {
		Employee employee2 = employee.get();
		employeeRepository.save(employee2);
		return "employee added successfully";
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

}
