package in.nit.ganesh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.ganesh.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
