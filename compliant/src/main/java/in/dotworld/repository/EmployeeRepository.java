package in.dotworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.dotworld.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
