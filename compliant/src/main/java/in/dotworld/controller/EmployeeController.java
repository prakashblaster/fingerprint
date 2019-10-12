package in.dotworld.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.dotworld.model.Employee;
import in.dotworld.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/addEmp")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String addEmployee(@RequestBody Optional<Employee> employee) {
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/updateName/{no}/{name}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String updateName(@RequestBody Optional<Employee> emp, @PathVariable String name, @PathVariable int no) {
		return employeeService.updateName(no, name, emp);
	}

}
