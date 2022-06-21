package in.nit.ganesh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.ganesh.entity.Employee;
import in.nit.ganesh.exception.EmployeeNotFoundException;
import in.nit.ganesh.repo.EmployeeRepository;
import in.nit.ganesh.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public Integer saveEmployee(Employee employee) {
		return repo.save(employee).getEmpId();
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> list = repo.findAll();
		return list;
		
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Optional<Employee> opt = repo.findById(id);
		
		//Employee employee = opt.orElseThrow(()->new EmployeeNotFoundException("Employee Not Exist.."));
		
		  Employee emp = null; 
		  if(opt.isPresent()) 
		  {
			  emp = opt.get();
		}else {
			throw new EmployeeNotFoundException("Employee Not Found..");
		}
		 
		return emp;
	}

	@Override
	public void deleteEmployee(Integer id) {
		repo.delete(getOneEmployee(id));
	}

	@Override
	public void updateEmployee(Employee employee) {
		if(getOneEmployee(employee.getEmpId()) != null) {
			repo.save(employee);
		}
	}

}
