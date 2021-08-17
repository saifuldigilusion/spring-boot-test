package hk.com.nexify.rest.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hk.com.nexify.cmn.response.ResponseModel;
import hk.com.nexify.entity.test.Employee;
import hk.com.nexify.service.EmployeeService;

@Controller
@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/list")
	public ResponseModel<List<Employee>> list() {
		return new ResponseModel<List<Employee>>(employeeService.listEmployees());
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseModel<Employee> detail(@PathVariable Long id) {
		return new ResponseModel<Employee>(employeeService.get(id));
	}
	
	@PostMapping("/add")
	public ResponseModel<Employee> add(@RequestBody Employee employee) {
		return new ResponseModel<Employee>(employeeService.add(employee));
	}
	
	@PutMapping("/update")
	public ResponseModel<Employee> update(@RequestBody Employee employee) {
		return new ResponseModel<Employee>(employeeService.update(employee));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseModel delete(@PathVariable Long id) {
		employeeService.delete(id);
		return new ResponseModel();
	}
}