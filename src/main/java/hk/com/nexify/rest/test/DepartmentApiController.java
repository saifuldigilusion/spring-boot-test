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
import hk.com.nexify.entity.test.Department;
import hk.com.nexify.service.DepartmentService;

@Controller
@RestController
@RequestMapping("/api/department")
public class DepartmentApiController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping(value = "/list")
	public ResponseModel<List<Department>> list() {
		return new ResponseModel<List<Department>>(departmentService.listDepartments());
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseModel<Department> detail(@PathVariable Long id) {
		return new ResponseModel<Department>(departmentService.get(id));
	}
	
	@PostMapping("/add")
	public ResponseModel<Department> add(@RequestBody Department department) {
		return new ResponseModel<Department>(departmentService.add(department));
	}
	
	@PutMapping("/update")
	public ResponseModel<Department> update(@RequestBody Department department) {
		return new ResponseModel<Department>(departmentService.update(department));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseModel delete(@PathVariable Long id) {
		departmentService.delete(id);
		return new ResponseModel();
	}
}