package in.nit.ganesh.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.nit.ganesh.entity.Employee;
import in.nit.ganesh.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		ResponseEntity<String> resp = null;
		String message = null;
		try {
		Integer id = service.saveEmployee(employee);
		message = "Employee "+id+" Created";
		resp = new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			message = "Employee not saved";
			resp = new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployee(){
		ResponseEntity<?> response= null;
		try {
			List<Employee> list = service.getAllEmployee();
			response = new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>("Emplopyee Records not present..", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable Integer id){
		ResponseEntity<?> response= null;
		try {
			Employee emp = service.getOneEmployee(id);
			response = new ResponseEntity<Employee>(emp,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>("Emplopyee Records not present..", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		ResponseEntity<String> resp = null;
		try {
			service.deleteEmployee(id);
			resp = new ResponseEntity<String>("Employee Deleted successfully..", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Employee not found..",HttpStatus.BAD_REQUEST);
		}
		return resp;
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
		ResponseEntity<String> resp = null;
		try {
			service.updateEmployee(employee);
			resp = new ResponseEntity<String>("Employee updated successfully..",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Not Updated..",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	
	
	
	
	
	
	
	
}
