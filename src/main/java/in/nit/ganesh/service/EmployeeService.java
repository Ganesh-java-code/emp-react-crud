package in.nit.ganesh.service;

import java.util.List;

import in.nit.ganesh.entity.Employee;

public interface EmployeeService {
	
	Integer saveEmployee(Employee employee);
	List<Employee> getAllEmployee();
	Employee getOneEmployee(Integer id);
	void deleteEmployee(Integer id);
	void updateEmployee(Employee employee);

}
